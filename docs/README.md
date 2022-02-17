# User Guide

## Features 

### Duke Chat-Bot (Task tracker)

The Duke Chat-Bot is an interactive task tracker. It supports
create, read, update and delete functionalities. Your tasks
are automatically saved by Duke and loaded up in future uses
of the Chat-Bot.

The tasks you are able to add can be basic with just a task
name, or tagged with some deadline or occurrence date.

Tasks are also mark-able to indicate if they have been completed
or not.

You can list all your tasks, as well as filter them based on 
their names or tagged date.

### Duke Clients (Client Management System)

The Duke Clients handler is a fully-GUI component. In this GUI,
we can add new clients and view them in a simple list. This has
only the basic feature of creating and listing all clients.

## Usage: Duke Chat-Bot

### `todo` - Add ToDo Task
 
Adds a ToDo task item to the task list. A ToDo task item is
the most basic task type, containing only a task name, and
its completed status.

Format: `todo TASKNAME`

Examples:
- `todo buy vegetables`
- `todo jog`
- `todo feed the pets in my room`

  If successful, the to do item itself will be sent by Duke.
  Otherwise, the error will be shown.

```aidl
#1: [T][ ] jog
```

### `event` - Add Event Task

Adds an Event task item to the task list. An Event task item
is the same as a ToDo task item, but has some date associated
with it. The date indicates the occurrence of the Event item.

Format: `event TASKNAME /at DD-MM-YYYY (hh:mm)`

> 💡 **Tip**: The timestamp is will be set to `00:00`  by 
> default if no timestamp was supplied.

Examples:
- `event powerlifting meet /at 22-02-2022 14:00`

Expected outcome:

If successful, the event item itself will be sent by Duke. 
Otherwise, the error will be shown.

```aidl
#1: [E][ ] powerlifting meet (at: Feb 22 2022, 02:00 PM)
```

### `deadline` - Add Deadline Task

Adds a Deadline task item to the task list. A deadline task item
is the same as a ToDo task item, but has some date associated
with it. The date indicates the deadline for the Deadline item.

Format: `deadline TASKNAME /by DD-MM-YYYY (hh:mm)`

> 💡 **Tip**: The timestamp is will be set to `00:00`  by
> default if no timestamp was supplied.

Examples:
- `deadline complete individual project /by 18-02-2022 23:59`

Expected outcome:

If successful, the deadline item itself will be sent by Duke.
Otherwise, the error will be shown.

```aidl
#1: [D][ ] complete individual project (by: Feb 18 2022, 11:59 PM)
```

### `list` - Show all Tasks

Lists out all the tasks the user has added in a simple, readable and 
user-friendly format. If you include the optional date, only tasks with
a date, and whose date matches the supplied input will be shown.

Format: `list (date DD-MM-YYYY)`

Examples:
- `list`
- `list date 18-02-2022`

Expected outcome:

If successful, the all items will be formatted and sent by Duke.
Otherwise, the error will be shown.

```aidl
> list
#1: [D][ ] complete individual project (by: Feb 18 2022, 11:59 PM)
#2: [E][ ] powerlifting meet (at: Feb 22 2022, 02:00 PM)

> list date 18-02-2022
#1: [D][ ] complete individual project (by: Feb 18 2022, 11:59 PM)
```

### `find` - Show Tasks filtered by search query

Filters all the tasks the user has created by their task name against
the given search query by the user, then lists them in a similar format
to the `list` command. Partial matches will be shown as well.

Format: `find TASKNAME`

Examples:
- `find buy`

Expected outcome:

If successful, the all items will be formatted and sent by Duke.
Otherwise, the error will be shown.

```aidl
#1: [T][ ] buy groceries
```

### `mark` - Consider task done

Allows the user to set a task as done, for their own reference.

Format: `mark INDEX`

Examples:
- `mark 1`

Expected outcome:

If successful, the marked item will be formatted and sent by Duke.
Otherwise, the error will be shown.

```aidl
> list
#1: [T][ ] buy groceries
#2: [T][ ] water plants
> mark 1
#1: [T][X] buy groceries
```

### `unmark` - Consider task undone

Allows the user to set a task as undone, for their own reference.

Format: `unmark INDEX`

Examples:
- `unmark 1`

Expected outcome:

If successful, the unmarked item will be formatted and sent by Duke.
Otherwise, the error will be shown.

```aidl
> list
#1: [T][X] buy groceries
#2: [T][ ] water plants
> unmark 1
#1: [T][ ] buy groceries
```

### `delete` - Delete the Task.

Removes the task from the user's list of tasks completely.

Format: `delete INDEX`

Examples:
- `delete 1`

Expected outcome:

If successful, the unmarked item will be formatted and sent by Duke.
Otherwise, the error will be shown.

```aidl
> list
#1: [T][X] buy groceries
#2: [T][ ] water plants
> delete 1
Task has been deleted: [T][X] buy groceries
> list
#1: [T][ ] water plants
```

## Usage: Duke Clients

### `Add` - Add a new client

Clicking on the `Add` button will cause a pop-up to appear. This pop-up will
allow users to add a first name, last name, contact and gender of the client.
However, only the first name is a mandatory field. 

Once the user has added the client successfully, they will be shown in a simple
list of client values in the middle of the page.
