# SecretSantaCalculator

This is a repository designed to generate a secret santa list and email the participants randomly.

The main class is SecretSantaService, and it takes in two args, the first one being your smtp username and the second being the password. Currently, smtp.gmail.com is the default provider.

To configure who gets emailed, modify the people.txt file, which is in csv format. The first parameter is name, the second is their email, and the third is whatever specialized information they have provided (gift ideas, etc).

To modify the email body, look in EmailService, approximatly lines 41-43.

For contributing: The current main area that needs to be improved is the customizability of the application. The long term goal is to rely on configuration files and not have to make any code changes when customizing for a different use case.
