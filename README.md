**REFLECTION 1**



Secure coding practices I did:

* I separated the code into controller, service, and repository so it's easier to read
* Each class has its own job.
* I used UUID for product ID so every product has a unique ID
* I used Thymeleaf to show data in HTML.
* I used redirect after create, edit, and delete to avoid duplicate actions



**REFLECTION 3**



1. &nbsp;Explain what principles you apply to your project!



* I separated the code into controller, service, and repository layers so each layer has a clear responsibility. Each class only has one main job, such as handling requests, processing business logic, or managing data (SRP).
* I used interfaces for service and repository to allow different implementations without changing existing code (OCP).
* The implementation classes can replace their interfaces without breaking the system (LSP).
* I separated large interfaces into smaller ones so classes only depend on the methods they need (ISP).
* The service depends on repository interfaces instead of concrete classes to reduce tight coupling (DIP).



2\. Explain the advantages of applying SOLID principles to your project with examples.



* Improves code organization by clearly separating responsibilities.
* Makes the system easier to understand and read.
* Increases maintainability because changes in one part do not heavily affect other parts.
* Reduces tight coupling between components.
* Makes the system more flexible and easier to extend with new features.
* Improves testability since dependencies can be replaced with mock implementations.
* Helps prevent large, complex classes that are difficult to manage.



3\. Explain the disadvantages of not applying SOLID principles to your project with examples.



* Leads to tightly coupled code, making changes risky and difficult.
* Creates large classes that handle too many responsibilities.
* Makes debugging and maintenance more complicated.
* Reduces flexibility when adding new features or modifying existing ones.
* Makes unit testing harder due to strong dependencies between components.
* Increases the chance of introducing bugs when modifying the system.
* Results in rigid and fragile system architecture as the project grows.



**REFLECTION 4**



1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.



The TDD flow was useful because by writing the tests first, it gives a clear picture on how the src file codes should be like.



2\. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.



F: My tests run quickly

I: Tests don't depend on each other

R: Same result every time

S: Uses assertEquals, assertThrows, etc.

T: 



