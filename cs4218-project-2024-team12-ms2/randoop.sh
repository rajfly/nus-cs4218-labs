# javac -classpath ./src -d ./src ./src/**/*.java
# find ./src/sg/edu/nus/comp/cs4218/impl -name "*.java" | sed 's/^\.\///; s/\.java$//; s/\//./g' > test-classes.txt

java -classpath ./src:randoop-all-4.3.2.jar \
randoop.main.Main gentests \
--classlist=test-classes.txt \
--time-limit=30 \
--only-test-public-members=true \
--testsperfile=50 \
--input-selection=SMALL_TESTS \
--forbid-null=true \
--junit-package-name=randoop \
--junit-output-dir=test