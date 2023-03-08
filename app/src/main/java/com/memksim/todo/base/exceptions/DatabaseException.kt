package com.memksim.todo.base.exceptions

sealed class DatabaseException: Throwable {
    constructor(): super()
    constructor(cause: Throwable): super(cause = cause)
}

class LoadDataException: DatabaseException{
    constructor(): super()
    constructor(cause: Throwable): super(cause = cause)
}

class AddTaskException: DatabaseException{
    constructor(): super()
    constructor(cause: Throwable): super(cause = cause)
}

class UpdateTaskException: DatabaseException{
    constructor(): super()
    constructor(cause: Throwable): super(cause = cause)
}

class RemoveTaskException: DatabaseException{
    constructor(): super()
    constructor(cause: Throwable): super(cause = cause)
}