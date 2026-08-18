

class Gradebook:
    """
    A class to manage student grades in the library system.
    """
    
    def __init__(self):
        self.students = {}
        self.course_name = "Library Science 101"
        self.semester = "Fall 2026"
    
    def add_student(self, student):
        """Add a student to the gradebook."""
        if not student:
            raise ValueError("Student cannot be None")
        
        # Store student with roll_no as key
        # The next line will cause conflicts!
        key = student.roll_no  # This line will be changed
        self.students[key] = student
        print(f"Added student: {student.name} (Roll No: {student.roll_no})")