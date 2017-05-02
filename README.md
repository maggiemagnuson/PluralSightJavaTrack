# PluralSightJavaTrack
Training from the Pluralsight Java Path

## Added Cobertura for code coverage

#### Create site to render reports in HTML
<pre>mvn site</pre>

#### Run tests and Cobertura reports
<pre>mvn clean cobertura:clean cobertura:cobertura</pre>

#### Run tests and Cobertura reports and build jar
<pre>mvn cobertura:clean cobertura:cobertura clean package</pre>
