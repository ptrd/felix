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

## Implicit callbacks

The DependencyManager has a number of implicit callbacks (hooks), that you might or might not use. If you don't use them, you can disable them, but most people don't do that as it causes no harm. However, this version of the DependencyManager will complain if the callbacks are missing and not disabled (that's why it's called pedantic). To get rid of these errors, ensure that you always set the right callback methods on the `Component`, e.g.

    dependencyManager.createComponent().setCallbacks(null, null, null, null);
    
to disable them all.

## Log levels

All detected issues are logged at error or warning level:


* `ERROR`: any misconfiguration that will lead to errors in your application, or "risky business" that _might_ lead to errors in your application (it might as well not lead to errors, but the whole point of this version is to avoid risks, and therefore it is logged as error); example: misspelled callback name, injection fields not being declared volatile
* `WARNING`: any construct that should be avoided, but that might have valid use in some applications, e.g. callback methods not being declared as private.


## List of errors

The following errors are detected by this version:

* failing injection of (auto-config) service, due to missing field of matching type
* failing injection of (auto-config) service in named field (`Dependency.setAutoConfg(String)`), due to missing field / misspelled field name
* injection field declared as static: obviously, this might lead to confusing behaviour when the implementation class is instantiated more than once
* injection field not being declared as volatile: because injection is not synchronized, injection fields should always be declared as volatile
* injection field not being declared as private: having such fields accessible by others is usually a bad idea, because updates to the field cannot be detected; you're better off using callbacks to let you notify of updates (`WARNING`) 
* missing callback methods, either implicit (Component callbacks) as explicit (Dependency callbacks)
* callbacks methods with incorrect signature (won't be called)

## Status

This fork tracks the original. Since it started, there have been no DependencyManager releases yet. 
The latest commit from the original Felix repository that is present is this repository too, is
<https://svn.apache.org/repos/asf/felix/trunk@1529197>.