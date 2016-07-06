# strings-token-macro-plugin

## Introduction
This plugin is a producer of the [Token Macro Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Token+Macro+Plugin/ "Token Macro Plugin"), and its purpose is to allow 
the user to perform string manipulations upon the token value.

## Usage

### Substring
Substring will take the substring based on the start and end of the string that is passed in as value.  It is null safe.

#### Basic
```
/**
 *  value-  must be of type string
 *  start - [OPTIONAL] defaults to 0; this is the beginning index of the substring to start
 *  end - this is the end index of the string
 **/
${SUBSTRING, value=string, start=num1, end=num2} 

/**
 * EXAMPLE
 **/
${SUBSTRING, value="This is some arbitrary value", end=8} 
//RESULT - 'This is ' 
```

#### Environment variables
Substring can be used with environmental variables as long as the variable is prefaced with 'env:', in which case the environmental variable will be queried and the 
substring applied.

```
/**
 * EXAMPLE
 *   GIT_COMMIT is stored as an environment variable that is passed as a downstream parameter and all you want to get is the first 8 characters
 **/
${SUBSTRING, value="env:GIT_COMMIT", end=8}
```
