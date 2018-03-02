# X-Team 73 Style Guide

<brief description of your team's opinion or philosophy regarding Style Guides>

## Naming conventions

* Identifiers use only ASCII letters and digits, and underscores in a some constants.
* Class and Interface names are written in UpperCamelCase. Class names are typically nouns or noun phrases.
* Methods, fields and local variable names are written in lowerCamelCase. Method names are typically verbs or verb phrases. 
* Constant names use CONSTANT_CASE: all uppercase letters, with words separated by underscores.

### Examples
* interfaces: Comparable
* classes: RedBlackTree
* exception types: RuntimeException
* fields:  private int lowerCamelCaseNoun;
* methods: private void makeNameVerbInLowerCamelCase(){}
* parameters: private void test(int lowerCamelCaseNoun){}
* local variables: firstName, orderNumber
* instance constants: RED, YELLOW, MAX_PRIORITY
* class constants: RED, YELLOW, MAX_PRIORITY

## Commenting style for public and private members of a class or interface:

Use comments within source files to:

* highlight the major steps of your algorithm
* explain long calculations or conditions tied to unobvious state
* clarify convoluted or unusual code
* clarify the purpose of non-temporary local variables and fields
* mark locations where you suspect a bug may exist
* mark locations where improvements or enhancements are planned

### Examples

* classes
* fields
* constructors
* methods
* coding style (brackets, horizontal, and vertical spacing) for:
  * if statements: let the bracket follow with the if statement in the same line and then hit ENTER:
  * if(1==0){
  *   System.out.print("This is the start of a NEW WORLD!");
  * }
  * switch statement
  * while loops
  * for loops
  * enhanced for loops
