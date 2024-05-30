cd ..
$teamcity_tests_directory = pwd
echo $teamcity_tests_directory
$workdir = "teamcity_tests_infrastructure"
$teamcity_server_workdir = "teamcity_server"
$teamcity_agent_workdir = "teamcity_agent"
$selenoid_workdir = "selenoid"
$teamcity_server_container_name = "teamcity_server_instance"
$teamcity_agent_container_name = "teamcity_agent_instance"
$selenoid_container_name = "selenoid_instance"
$selenoid_ui_container_name = "selenoid_ui_instance"

$workdir_names = ($teamcity_server_workdir, $teamcity_agent_workdir, $selenoid_workdir)
$container_names = ($teamcity_server_container_name, $teamcity_agent_container_name, $selenoid_container_name, $selenoid_ui_container_name)
#################################
$ip = ipconfig | Select-String "IPv4 Address" | Select-Object -First 1 | ForEach-Object { $_.ToString().Split(':')[1].Trim() }
echo $ip
$http = "http://"
#################################
echo "Delete previous run data"

Remove-Item -Recurse -Force $workdir
mkdir $workdir
cd $workdir
#
foreach ($dir in $workdir_names)
{
    New-Item -ItemType Directory -Path $dir -Force
}
#
foreach ($container in $container_names)
{
    docker stop $container
    docker rm $container
}
#################################
echo "Start teamcity server"

cd $teamcity_server_workdir

docker run -d --name $teamcity_server_container_name -v $pwd/logs:/opt/teamcity/logs -p 8111:8111 jetbrains/teamcity-server

echo "Teamcity Server is running..."
#
#################################
cd ..
echo "Start selenoid"
cd $selenoid_workdir
mkdir config
Copy-Item -Path $teamcity_tests_directory/infra/browsers.json -Destination config/

docker run -d --name $selenoid_container_name -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v $pwd/config/:/etc/selenoid/:ro aerokube/selenoid:latest-release

$content = Get-Content $pwd'/config/browsers.json'

$image_names = @()

foreach ($line in $content)
{
    if ($line -match 'image')
    {
        $line = $line -split ':', 2
        $line = $line[1].Trim(' ', ',')
        $image_names += $line
    }
}

$image_names
Write-Host "Pull all browser images: "$image_names = @()

foreach ($image in $image_names)
{
    docker pull $image
}

#################################
echo "Start selenoid-ui"


docker run -d --name $selenoid_ui_container_name -p 8080:8080 aerokube/selenoid-ui --selenoid-uri $http$ip":4444"

################################
echo "Setup teamcity server"
#            ################################
cd ..
cd ..
mvn clean test -Dtest=SetupTest#startUpTest
echo "Parse superuser token"
$string = (Get-Content $teamcity_tests_directory/$workdir/$teamcity_server_workdir/logs/teamcity-server.log) -match 'Super user authentication token: [0-9]*' | Select-Object -Last 1
$lastColonIndex = $string.LastIndexOf(":")
$result = $string.Substring($lastColonIndex + 1).Trim()
$superuser_token = $result.Split("(")[0].Trim()
Write-Host "Super user token: $superuser_token"

$port = ":8111"
$ipwithport = "$ip$port"
$filePath = Join-Path -Path $teamcity_tests_directory -ChildPath 'src\main\resources\config.properties'
$properties_content ="host=$ipwithport
superUserToken=$superuser_token
remote=http://localhost:4444/wd/hub
browser=firefox"

Set-Content -Path $filePath -Value $properties_content
echo $filePath
echo $properties_content

echo "Run system tests"
echo "Run API tests"
mvn clean test -DsuiteXmlFile="testng-suites/api-suite.xml"

echo "Run UI tests"
mvn test -DsuiteXmlFile="testng-suites/ui-suite.xml"

cd $workdir

echo "Start teamcity agent"

cd $teamcity_agent_workdir

docker run -d --name $teamcity_agent_container_name -e SERVER_URL=$http$ip":8111" -v $pwd/conf:/data/teamcity_agent/conf jetbrains/teamcity-agent

echo "Teamcity agent is running..."
cd ..
cd ..
echo "Teamcity agent Tests is running..."
mvn clean test -Dtest=AutirizeAgentTest#autorizeAgentTest

foreach ($container in $container_names)
{
    docker stop $container
}
