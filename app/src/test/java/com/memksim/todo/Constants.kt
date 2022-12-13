package com.memksim.todo

import com.memksim.todo.domain.model.ReminderDto

val fakeUpcomingDtoList: List<ReminderDto> = listOf(
    ReminderDto(
        1,
        "dto1",
        "note...",
        "01.01.2023",
        "23:59",
        false
    ),
    ReminderDto(
        2,
        "dto2",
        "note...",
        "01.01.2023",
        "23:59",
        false
    ),
    ReminderDto(
        3,
        "dto3",
        "note...",
        "01.01.2023",
        "23:59",
        false
    ),
    ReminderDto(
        4,
        "dto4",
        "note...",
        "01.01.2023",
        "23:59",
        false
    ),
    ReminderDto(
        5,
        "dto5",
        "note...",
        "01.01.2023",
        "23:59",
        false
    )
)

val fakeCompletedDtoList: List<ReminderDto> = listOf(
    ReminderDto(
        1,
        "dto1",
        "note...",
        "01.01.2023",
        "23:59",
        true
    ),
    ReminderDto(
        2,
        "dto2",
        "note...",
        "01.01.2023",
        "23:59",
        true
    ),
    ReminderDto(
        3,
        "dto3",
        "note...",
        "01.01.2023",
        "23:59",
        true
    ),
    ReminderDto(
        4,
        "dto4",
        "note...",
        "01.01.2023",
        "23:59",
        true
    ),
    ReminderDto(
        5,
        "dto5",
        "note...",
        "01.01.2023",
        "23:59",
        true
    )
)

val fakeDto = ReminderDto(
    1,
    "dto1",
    "note...",
    "01.01.2023",
    "23:59",
    false
)