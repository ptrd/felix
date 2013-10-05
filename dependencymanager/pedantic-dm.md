# Pedantic-DM

This is a fork of the powerfull [Apache Felix DependencyManager]() that adds pedantic misconfiguration warnings. The original DependencyManager is quite forgiving with resepect to misconfiguration, which can be very frustrating when it does not work as expected. The intent of this fork is to issue errors and warnings for every possible configuration or usage error. Apart from this, this fork does not add _any_ functionality.

## Compatability

Any build of this fork should functionally be 100% compatible with the original.

## List of errors

The following errors are detected by this version:

* failing injection of (auto-config) service, due to missing field of matching type
* failing injeciton of (auto-config) service in named field (`Dependency.setAutoConfg(String)`), due to missing field / misspelled field name

