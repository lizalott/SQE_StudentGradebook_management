

class Gradebook:
    """
    A class to manage student grades in the library system.
    """
    
    def __init__(self):
        self.students = {}
        self.course_name = "Library history 101"
        self.tenure = " 2026"
    
    def add_student(self, student):
        """Add a student to the gradebook."""
        if not student:
            raise ValueError("Student cannot be None")
        
        # Store student with student_id as key
        key = student.id_number   
        self.students[key] = student
        print(f"Added student: {student.name} (Roll No: {student.id_number})")
        
        
        