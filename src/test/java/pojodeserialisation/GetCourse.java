package pojodeserialisation;

public class GetCourse {

		private String url;	
		private String services;
		private String expertise;
		//As courses is sub Json we need to inject the class courses here and change the return type of courses from string to courses class
		private Courses courses;
		private String instructor;
		private String LinkedIn;
		
		//Alt+shift+s to get the getters and setters
		
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getServices() {
			return services;
		}
		public void setServices(String services) {
			this.services = services;
		}
		public String getExpertise() {
			return expertise;
		}
		public void setExpertise(String expertise) {
			this.expertise = expertise;
		}
		public Courses getCourses() {
			return courses;
		}
		public void setCourses(Courses courses) {
			this.courses = courses;
		}
		public String getInstructor() {
			return instructor;
		}
		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}
		public String getLinkedIn() {
			return LinkedIn;
		}
		public void setLinkedIn(String linkedIn) {
			LinkedIn = linkedIn;
		}
		
		
	

}
