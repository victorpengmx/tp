---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Connectify User Guide

Connectify is a networking platform designed to help professionals efficiently manage their connections and enhance their networking experiences. In this version (v1.2), we have focused on delivering the essential features for a Minimum Viable Product (MVP): **adding contacts**, **deleting contacts**, and **listing contacts**. This guide will walk you through the core functionality of Connectify.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  * [Adding Contacts](#adding-contacts)
  * [Deleting Contacts](#deleting-contacts)
  * [Editing Contacts](#editing-people)
  * [Listing Contacts](#listing-all-entities)
  * [Exit](#exit)
* [FAQ](#faq)
* [Known issues](#known-issues)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Create a new folder to store Connectify.
3. Download Connectify from the following link and save the downloaded file in the directory you created in the previous step.
4. Launch Connectify by double-clicking the file in the directory. After a brief moment, the Connectify application will commence. You'll be greeted by Connectify's primary interface, complete with sample contacts and company profiles already included for your exploration.

Now that you've successfully installed Connectify, let's take a moment to familiarize ourselves with the application's interface and functionality before diving into its features.
[More Details Coming Soon]
--------------------------------------------------------------------------------------------------------------------

## Features

### Adding Contacts
#### Command: `addPerson`

The `addPerson` command allows you to add new contacts to your Connectify database. Follow the format below to add contacts:

```
addPerson n/NAME p/PHONE e/EMAIL a/ADDRESS c/COMPANY [t/TAG]
```
The fields are:
- `n/NAME` is the name of the contact.
- `p/PHONE` is the phone number of the contact.
- `e/EMAIL` is the email address of the contact.
- `a/ADDRESS` is the address of the contact.
- `c/COMPANY` is the index of the company to which the contact belongs.
- `[t/TAG]`: (optional) is used to categorize your contacts. Tags help organize connections efficiently.

**Example:**

To add a contact named John Doe with the phone number 98765432, email address johndoe@example.com, address "311, Clementi Ave 2, #02-25," and associate them with Company 1, use the following command:

```
addPerson n/John Doe p/98765432 e/johndoe@example.com a/311, Clementi Ave 2, #02-25 c/1 t/friends t/owesMoney
```

**Successful Output:**

```
New person added: John Doe
Phone: 98765432
Email: johnd@example.com
Address: 311, Clementi Ave 2, #02-25
Company: Company 1
Tags: friends, owesMoney
```

**Unsuccessful Output:**

- If the provided details are incomplete or invalid:
  ```
  Please provide valid contact details including name, phone, email, address, and a valid company index.
  ```

- If a person with the same details already exists in the address book:
  ```
  This person already exists in the address book.
  ```

- If the company index provided is invalid:
  ```
  The company index provided is invalid.
  ```

#### Command: `addCompany`

The **addCompany** command allows you to add new companies to your Connectify database. Follow the format below to add contacts:

`addCompany n/NAME i/INDUSTRY l/LOCATION d/DESCRIPTION w/WEBSITE e/EMAIL p/PHONE a/ADDRESS`

- `n/NAME`: Specify the Company name.
- `i/INDUSTRY`: Add the industry the Company is in.
- `l/LOCATION`: Add the city/locale Company is at.
- `d/DESCRIPTION`: Describe the Company.
- `w/WEBSITE`: Add the Company website.
- `e/EMAIL`: Add the contact email of the Company.
- `p/PHONE`: Add the phone number of the Company.
- `a/ADDRESS`: Include the address of the Company.

**Example:**

To add a Company "Apple Inc", a computer and consumer technology company headquartered in Los Altos, with the website "www.apple.com", email address contact@apple.com, the phone number 98765432, address "1 Apple Park Way, Cupertino, California", use the following command:

```
addCompany n/Apple Inc i/technology l/Los Altos d/computer and consumer technology w/www.apple.com e/contact@apple.com p/98765432 a/1 Apple Park Way, Cupertino, California
```

**Successful Output:**

```
New company added: Apple Inc
Phone: 98765432
Email: contact@apple.com
Address: 1 Apple Park Way, Cupertino, California
Industry: technology
Location: Los Altos
Description: computer and consumer technology
Website: www.apple.com
```

**Unsuccessful Output:**

- If a person with the same details already exists in the address book:
  ```
  This company already exists in the address book.
  ```

### Deleting Contacts

#### Command: `deletePerson`

The **deletePerson** command allows you to remove people from your Connectify database. To delete a person, you need to specify the person's index.

```
deletePerson INDEX
```

- `INDEX`: Provide the index of the person you want to delete.

**Example:**

To delete the person named John Doe at index 1, use the following command:
```
deletePerson 1
```

**Successful Output:**

```
Noted. I've removed this contact:
John Doe
Email:
```

**Unsuccessful Output:**

```
Please provide a valid index.
```

#### Command: `deleteCompany`

The **deleteCompany** command allows you to remove companies from your Connectify database. To delete a company, you need to specify the company's index.

```
deleteCompany INDEX
```

- `INDEX`: Provide the index of the company you want to delete.

**Example:**

To delete the company named Test Company at index 1, use the following command:
```
deleteCompany 1
```

**Successful Output:**

```
Deleted Company: Test Company
```

**Unsuccessful Output:**

```
The company index provided is invalid.
```

### Editing People
####  Command: `edit`

The `edit` command allows you to modify the details of a person in your Connectify database. Follow the format below to edit contacts.

```
edit INDEX c/COMPANY [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]
```
The fields are:
- `INDEX` is the index of the person in the displayed person list of a company that you want to edit. It must be a positive integer within the company.
- `c/COMPANY`is the index of the company that the person is associated with.
- `n/NAME` is the new name of the person.
- `[p/PHONE]` (optional) is the person's new phone number.
- `[e/EMAIL]` (optional) is the person's new email.
- `[a/ADDRESS]` (optional) is the person's new address.
- `[pr/PRIORITY]` (optional) is the person's new priority level.
- `[t/TAG]` (optional) is the person's new tags.

**Example:**
To edit the person's name to "NewName," their phone number to "98765432," and their email to "newemail@example.com." The person is located at index 1 in the displayed person list of Company 1, and the person is associated with Company 1. Use the following command:
```
edit 1 c/1 n/NewName p/98765432 e/newemail@example.com
```

**Successful Output**
```
Edited Person: NewName
```

**Unsuccessful Output**
- If you don't provide at least one field to edit, you will receive the following error message:
```
At least one field to edit must be provided.
```
- If the specified index is invalid (not within the displayed person list or not a positive integer), you will get the following error message:
```
The person index provided is invalid.
```
- If the specified company index is missing, you will get the following error message:
```
No company provided.
```

- If you try to edit a person's details in a company that does not exist, you will receive:

```
The company index provided is invalid.
```

- If the specified company index or person index is out of bounds, you will get the following error message:
```
Invalid index provided.
```

- If you try to edit a person's details to match another person in the address book, you will receive the following error message:
```
This person already exists in the Connectify.
```

### Listing All Entities
####  Command: `list`

The `list` command allows you to retrieve a list of all entities (both persons and companies) in the Connectify database. This command is useful for obtaining an overview of all the entities you have stored.

Follow the format below to list all entities:
```
list
```
This command has no additional fields.

**Successful Output**
```
Listed all persons and companies.
```

**Unsuccessful Output**
- If Connectify is empty, and there are no entities to display, you will receive the following message:
```
There are no entities in Connectify.
```


### Listing All Companies
####  Command: `companies`
The `companies` command allows you to retrieve a list of all companies in Connectify. This command is particularly useful for obtaining an overview of all the companies you have stored.

Follow the format below to list all companies:
```
companies
```
This command has no additional fields.

**Successful Output**
```
Listed all companies.
```

**Unsuccessful Output**
-If Connectify does not contain any companies, and there are no companies to display, you will receive the following message:
```
There are no companies in Connectify.
```

### Listing All People
####  Command: `people`
The `people` command allows you to retrieve a list of all individuals (persons) in the Connectify. This command is especially useful for obtaining an overview of all the individuals you have stored.

Follow the format below to list all persons:
```
people
```
This command has no additional fields.

**Successful Output**
```
Listed all persons.
```

**Unsuccessful Output**
- If Connectify does not contain any individuals (persons), and there are no people to list, you will receive the following message:
```
There are no people in Connectify.
```


### Exit

#### Command: `exit`

This command exits the program.

**Successful Output:**

```
Bye. Hope to see you again soon!
```

**Unsuccessful Output:**

```
That is not a valid command.
```

[More Features Coming Soon]
>>>>>>> master

--------------------------------------------------------------------------------------------------------------------

## FAQ

[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Known issues

[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`

