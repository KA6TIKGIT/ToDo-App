package com.example.honeylemon

data class Course(
    val courseName: String,
    val courseCode: String,
    val instructor: String,
    val progress: Float
)

data class Assignment(
    val id: Int,
    val title: String,
    val course: String,
    val dueDate: String,
    val priority: String,
    val description: String = "",
    val reminderEnabled: Boolean = false,
    val isCompleted: Boolean = false
)

object SampleData {
    const val studentName = "Alex Johnson"
    const val studentId = "STU2024-0182"
    const val major = "Computer Science"

    val courseCodes = listOf("CS101", "MATH201", "ENG102", "PHY101")

    val courses = listOf(
        Course("Intro to Programming", "CS101", "Dr. Sarah Chen", 0.72f),
        Course("Calculus II", "MATH201", "Prof. Mark Rivera", 0.55f),
        Course("Academic Writing", "ENG102", "Dr. Emily Brooks", 0.40f),
        Course("Physics I", "PHY101", "Dr. Raj Patel", 0.85f)
    )

    // 7 pending + 2 completed, so the dashboard shows "Pending Tasks (7)"
    val assignments = listOf(
        Assignment(1, "Linked List Lab", "CS101", "Oct 14, 2026", "High"),
        Assignment(2, "Integration Problem Set", "MATH201", "Oct 16, 2026", "High"),
        Assignment(3, "Argumentative Essay Draft", "ENG102", "Oct 18, 2026", "Medium"),
        Assignment(4, "Kinematics Worksheet", "PHY101", "Oct 20, 2026", "Low"),
        Assignment(5, "Recursion Quiz Prep", "CS101", "Oct 22, 2026", "Medium"),
        Assignment(6, "Series & Sequences HW", "MATH201", "Oct 25, 2026", "Medium"),
        Assignment(7, "Lab Report 2", "PHY101", "Oct 28, 2026", "Low"),
        Assignment(8, "Hello World Project", "CS101", "Oct 02, 2026", "Low", isCompleted = true),
        Assignment(9, "Peer Review Reflection", "ENG102", "Oct 05, 2026", "Low", isCompleted = true)
    )
}
