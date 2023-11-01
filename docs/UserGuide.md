---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Connectify User Guide

Welcome to Connectify, your all-in-one networking companion designed specifically to empower SOC (School of Communication) students in efficiently managing their connections and enhancing their networking experiences. Whether you're a seasoned professional or just starting your networking journey, Connectify is here to help you build and maintain meaningful connections that can propel your career and personal growth.

Networking is a crucial aspect of your academic and professional life, and Connectify is here to simplify and streamline the process for you. This user guide will walk you through the key features and functionalities of Connectify, ensuring you make the most of this powerful networking platform.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  * [Adding Contacts](#adding-contacts)
  * [Deleting Contacts](#deleting-contacts)
  * [Editing Contacts](#editing-people)
  * [Listing Contacts](#listing-all-entities)
  * [Listing Companies](#listing-all-companies)
  * [Listing People](#listing-all-people)
  * [Exit](#exit)
* [FAQ](#faq)
* [Known issues](#known-issues)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

Getting started with Connectify is extremely simple! Here is a tutorial on how to set up Connectify in **4 easy steps**.

1. Firstly, ensure that you have *Java `11`* or above [installed](https://www.baeldung.com/java-check-is-installed) in your Computer.
2. Create a new folder to store Connectify.
3. Download Connectify from the following [**this link**](https://github.com/AY2324S1-CS2103T-W15-4/tp/releases) and save the downloaded file in the directory you created in the previous step.
4. Launch Connectify by double-clicking the file in the directory. You'll be greeted by Connectify's primary interface, with an empty database as shown below.
  <p style="text-align:center;"><img src="images/ConnectifyStartPage.png" width="500" /></p> <br>

Now that you've successfully installed Connectify, let's take a moment to familiarize ourselves with the application's interface and functionality before diving into its features!

<a href="#table-of-contents" class="return-to-toc-link">
  <span class="return-to-toc-text">Return to Table of Contents</span>
  <span class="return-to-toc-icon">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-bar-up" viewBox="0 0 16 16">
      <path fill-rule="evenodd" d="M3.646 11.854a.5.5 0 0 0 .708 0L8 8.207l3.646 3.647a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 0 0 0 .708zM2.4 5.2c0 .22.18.4.4.4h10.4a.4.4 0 0 0 0-.8H2.8a.4.4 0 0 0-.4.4z">
      </path>
    </svg>
  </span>
</a>

--------------------------------------------------------------------------------------------------------------------

## Features

### Features for managing company details

#### Adding a company: `addCompany`

The **addCompany** command allows you to add new companies to your Connectify database. To add a company's details, follow the format below:

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

#### Deleting a company: `deleteCompany`

The **deleteCompany** command allows you to remove companies from your Connectify database. To delete a company, follow the command format below:

```
deleteCompany INDEX
```

- `INDEX` is the index of the company you want to delete.

**Example:**

To delete the company named Test Company at index 1, use the following command:

```
deleteCompany 1
```

**Successful Output:**

```
Deleted Company: TechCorp;
Industry: Technology;
Location: Silicon Valley;
Description: Leading tech company;
Website: www.techcorp.com;
Email: contact@techcorp.com;
Phone: 12345678;
Address: 123 Tech St, Silicon Valley
```

**Unsuccessful Output:**

```
The company index provided is invalid.
```

#### Editing a company: `editCompany`

The **editCompany** command allows you to modify the details of a company in your Connectify database. To edit a company, follow the command format below:

```

```

**Example:**

To edit the company's name to "TechCorp", their email to techcorp@gmail.com and their address to "123, Jurong West Ave 6, #08-111", use the following command:

```
editCompany 1 n/TechCorp p/91234567 e/techcorp@gmail.com a/123, Jurong West Ave 6, #08-111
```

**Successful Output:**

```
Edited Company: TechCorp;
Industry: Technology;
Location: Silicon Valley;
Description: Leading tech company;
Website: www.techcorp.com;
Email: techcorp@gmail.com;
Phone: 91234567;
Address: 123, Jurong West Ave 6, #08-111;
```

**Unsuccessful Output:**

- If you don't provide at least one field to edit, you will receive the following error message:
```
At least one field to edit must be provided.
```

- If the specified index is invalid (not within the displayed company list or not a positive integer), you will get the following error message:
```
The company index provided is invalid.
```

####  Listing all companies: `companies`

The `companies` command allows you to retrieve a list of all companies in Connectify. This command is particularly useful for obtaining an overview of all the companies you have stored. To list all companies, follow the command format below:

```
companies
```

This command has no additional fields.

**Successful Output**

```
Listed all companies.
```

**Unsuccessful Output**

- If Connectify does not contain any companies, and there are no companies to display, you will receive the following message:
```
There are no companies in Connectify.
```

### Features for managing people contact details

#### Adding a person: `addPerson`

The `addPerson` command allows you to add new contacts to your Connectify database. Follow the format below to add contacts:

```
addPerson n/NAME p/PHONE e/EMAIL a/ADDRESS c/COMPANY pr/PRIORITY [t/TAG]
```

The fields are:
- `n/NAME` is the name of the contact.
- `p/PHONE` is the phone number of the contact.
- `e/EMAIL` is the email address of the contact.
- `a/ADDRESS` is the address of the contact.
- `c/COMPANY` is the index of the company to which the contact belongs.
- `pr/PRIORITY` is the priority level of the contact.
- `[t/TAG]`: (optional) is used to categorize your contacts. Tags help organize connections efficiently.

**Example:**

To add a contact named John Doe with the phone number 98765432, email address johndoe@example.com, address "311, Clementi Ave 2, #02-25," and associate them with Company 1, use the following command:

```
addPerson n/John Doe p/98765432 e/johndoe@example.com a/311, Clementi Ave 2, #02-25 c/1 t/friends pr/1 t/owesMoney
```

**Successful Output:**

```
New person added: John Doe
Phone: 98765432
Email: johnd@example.com
Address: 311, Clementi Ave 2, #02-25
Company: Company 1
Priority: 1
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

#### Deleting a person: `deletePerson`

The **deletePerson** command allows you to remove people from your Connectify database. To delete a person, follow the format below:

```
deletePerson COMPANY_INDEX PERSON_INDEX
```

- `COMPANY_INDEX` is the index of the company that the person is associated with.
- `PERSON_INDEX` is the index of the person you want to delete.

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

- If the specified company index is missing, you will get the following error message:
```
Invalid command format! 
deletePerson: Deletes the person identified by the index number from the specified company and also removes them from the address book.
Parameters: COMPANY_INDEX (must be a positive integer) PERSON_INDEX (must be a positive integer)
Example: deletePerson 2 1
```

- If the specified company index is invalid, you will get the following error message:
```
The company index provided is invalid.
```

- If the specified person index is invalid, you will get the following error message:
```
The person index provided is invalid.
```

####  Editing a person: `editPerson`

The `editPerson` command allows you to modify the details of a person in your Connectify database. To edit contacts, follow the format below:

```
editPerson INDEX c/COMPANY [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]
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
editPerson 1 c/1 n/NewName p/98765432 e/newemail@example.com
```

**Successful Output**

```
Edited Person: John Doe;
Phone: 12345678;
Email: johndoe@example.com;
Address: 311, Clementi Ave 2,
#02-25;
Tags: [owesMoney][friends]
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

####  Listing all people: `people`

The `people` command allows you to retrieve a list of all individuals (persons) in the Connectify. This command is especially useful for obtaining an overview of all the individuals you have stored. To list all people, follow the command format below:

```
people
```

Note: This command has no additional fields.

**Successful Output**

```
Listed all persons.
```

**Unsuccessful Output**

- If Connectify does not contain any individuals (persons), and there are no people to list, you will receive the following message:
```
There are no people in Connectify.
```

### Listing All Entities
####  Command: `list`

The `list` command allows you to retrieve a list of all entities (both persons and companies) in the Connectify database. This command is useful for obtaining an overview of all the entities you have stored.

Follow the format below to list all entities:
```
list
```
Note: This command has no additional fields.

**Successful Output**

```
Listed all persons and companies.
```

**Unsuccessful Output**

- If Connectify is empty, and there are no entities to display, you will receive the following message:
```
There are no entities in Connectify.
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

