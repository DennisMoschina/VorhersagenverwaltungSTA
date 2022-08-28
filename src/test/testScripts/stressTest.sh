setup() {
    mkdir -p -- "../../../build/testScripts"
}

loadAndValidate() {
    curl -s "$1" > "../../../build/testScripts/$2"
    python3 validate.py "../../../build/testScripts/$2"
}

setup

wait

loadAndValidate http://localhost:8080/catalogues/5/0 catalogueList.json &
loadAndValidate http://localhost:8080/catalogue/1 catalogue1.json &
loadAndValidate http://localhost:8080/catalogue/1/datasources/5/0 datasources.json &
loadAndValidate http://localhost:8080/catalogue/1/datasource/1 datasource1.json &
loadAndValidate http://localhost:8080/catalogue/1/datasource/2 datasource2.json &
loadAndValidate http://localhost:8080/catalogue/1/datasource/1/datastreams/25/0 datastreams_of_datasource1.json &
loadAndValidate http://localhost:8080/catalogue/1/datasource/2/datastreams/25/0 datastreams_of_datasource2.json &
loadAndValidate http://localhost:8080/catalogue/1/datasource/2/things/25/0 things_of_datasource2.json &

wait

echo "finished testing"