# Pedantic-DM

This is a fork of the powerfull [Apache Felix DependencyManager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html) that adds pedantic misconfiguration warnings. The original DependencyManager is quite forgiving with resepect to misconfiguration, which can be very frustrating when it does not work as expected. The intent of this fork is to create a version of the DependencyManager that logs errors and warnings for every possible configuration or usage error. Apart from this, this fork does not add _any_ functionality.

## Compatibility

Any build of this fork should functionally be 100% compatible with the original.

## Building

Go to the `dependencymanager` subproject and type

    mvn -Dpackaging=plugins -Dmaven.test.skip=true install
    

## Usage

Use as a temporary replacement for the original dependencymanager during development. All misconfigurations in your project should (and can!) be caught at development time, so there is no point at using this version in production. 

As with the messages logged by the original, all errors and warnings are logged to the LogService (if present) or the stdout if no LogService is present. If you don't have a LogService deployed, the [OSGi Screenlogger](https://bitbucket.org/pjtr/osgi-screen-logger) might be a convenient logging tool during development.


## List of errors

The following errors are detected by this version:

* failing injection of (auto-config) service, due to missing field of matching type
* failing injeciton of (auto-config) service in named field (`Dependency.setAutoConfg(String)`), due to missing field / misspelled field name

## Status

This fork tracks the original. Since it started, there have been no DependencyManager releases yet. 
The latest commit from the original Felix repository that is present is this repository too, is
<https://svn.apache.org/repos/asf/felix/trunk@1529197>.