package com.backend.elearning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.elearning.fileupload.FileResponce;
import com.backend.elearning.models.ApproveData;
import com.backend.elearning.models.Aproove;
import com.backend.elearning.models.Category;
import com.backend.elearning.models.ChaptersData;
import com.backend.elearning.models.Course;
import com.backend.elearning.models.CourseByCatIdData;
import com.backend.elearning.models.CourseCategory;
import com.backend.elearning.models.CourseData;
import com.backend.elearning.models.EnrollDetails;
import com.backend.elearning.models.EnrolledCourseData;
import com.backend.elearning.models.EnrolledCourses;
import com.backend.elearning.models.Order;
import com.backend.elearning.models.OrderData;
import com.backend.elearning.models.SubTopic;
import com.backend.elearning.models.SubTopicData;
import com.backend.elearning.models.Topic;
import com.backend.elearning.models.User;
import com.backend.elearning.models.UserData;
import com.backend.elearning.repositories.AprooveRepository;
import com.backend.elearning.repositories.ChapterRepository;
import com.backend.elearning.repositories.CourseRepository;
import com.backend.elearning.repositories.EnrollDetailsRepository;
import com.backend.elearning.repositories.EnrolledCourseRepository;
import com.backend.elearning.repositories.OrderRepository;
import com.backend.elearning.repositories.RegisterRepository;
import com.backend.elearning.services.ApproveService;
import com.backend.elearning.services.CourseService;
import com.backend.elearning.services.EmailSenderService;
import com.backend.elearning.services.EnrolledCoursesService;
import com.backend.elearning.services.FileService;

import com.backend.elearning.services.InstructorService;
import com.backend.elearning.services.OrderService;
import com.backend.elearning.services.RegisterService;
import com.backend.elearning.services.StudentService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/elearning")
public class MainController {

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private FileService fileService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private EnrolledCoursesService enrolledCoursesService;

	@Autowired
	private EnrolledCourseRepository enrolledCourseRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ApproveService approveService;

	@Autowired
	private AprooveRepository aprooveRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EnrollDetailsRepository enrollDetailsRepository;

	@Value("${project.image}")
	private String path; // getting file path from properties where image will store

	@PostMapping("/register")
	public String userRegistration(@RequestBody User userToRegister) {
		System.out.println("request hit" + userToRegister.toString());
		return registerService.register(userToRegister);
	}

	@PostMapping("/login")
	public String userLogin(@RequestBody User obj) {
		System.out.println("inside login");
		String username = obj.getUserName();
		String uname = registerService.getUserName(username);  //verify us
		System.out.println(username + " " + uname);

//		LoginInfo loginInfo = new LoginInfo();

		// password
		if (uname == null) {

//			loginInfo.setMessage("Username not found, Please Register First!");
			return "";
		} else {

			if (uname.equals(username)) {
				String pass = obj.getPass();
				String pwd = registerService.getPassword(uname);

				if (pwd == null) {
//					loginInfo.setMessage("Incorrect Password");
					return "";
				}

//				if (pwd.equals(pass)) {
					// get role id
					Integer roleid = registerService.getUserRoleId(uname);

//					loginInfo.setRoleId(roleid);
//					loginInfo.setUserName(uname);
//					loginInfo.setMessage("Login Sucessfull");

//					return uname;
//				} else {
//
////					loginInfo.setMessage("Incorrect Password");
//					return "";
//				}

			} else {
//				loginInfo.setMessage("Incorrect Password");
				return "";
			}
		}
		return uname;
	}

	// get rolename by username
	@PostMapping("/rolename")
	public String getUserRoleName(@RequestBody User user) {
		System.out.println("rolename=" + user.getUserName());
		return registerService.getUserRoleName(user.getUserName());
	}

	@PostMapping("/user/getuid/{userInfo}")
	public String getUserIdByUserName(@PathVariable String userInfo) {

		String result = registerService.getUserIDByUserName(userInfo);

		return result;
	}

	@PostMapping("/getuid")
	public String getUserIdByUserName(@RequestBody User user) {
		System.out.println("username=" + user.getUserName());
		String result = registerService.getUserIDByUserName(user.getUserName());
		System.out.println(result);
		return result;
	}

	@PostMapping("/addcourse")
	public String addNewCourse(@RequestBody Course course) {
		return instructorService.saveCourse(course);
	}

	@PostMapping("/addchapter")
	public String addNewChapter(@RequestBody Topic chapter) {
		return instructorService.saveChapter(chapter);
	}

	@PostMapping("/addsubtopic")
	public String addNewSubtopic(@RequestBody SubTopic subtopic) {
		return instructorService.saveSubtopic(subtopic);
	}

	@PostMapping("/file/uploadimg")
	public ResponseEntity<FileResponce> uploadFile(@RequestParam("image") MultipartFile image,
			@RequestParam("courseId") Course courseId, @RequestParam("userId") User userId,
			@RequestParam("chapterId") Topic chapterId, @RequestParam("subtId") SubTopic subtId) {
		System.out.println("file moule responce:");
		System.out.println("cid:" + courseId.getCourseId());
//		System.out.println("cid:" + userID);
		System.out.println("file name full:" + image.getOriginalFilename());

		String fileName = null;
//		MultipartFile image = course.getImage();
		try {
			fileName = fileService.uploadImage(path, image, courseId, userId, chapterId, subtId);
			System.out.println("filename: " + image.getName());

			// after upload save file path in respective table(update field value)
			System.out.println("path: " + path);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new FileResponce(null, "Video is could not upload"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(new FileResponce(fileName, "Video is successfully uploaded"), HttpStatus.OK);
	}

	// download image
	@GetMapping(value = "/download/{imageName}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		System.out.println("in download file");
		InputStream resource = fileService.getResources(path, imageName);
		response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());
	}

	// ======================== GET LISTS========================================

	@PostMapping("/getcourses")
	public List<String> getCoursesList(@RequestBody User user) {
		System.out.println("id:" + user.getUserId());
		return instructorService.getCoursesListByInstructorID(user.getUserId());
	}

	@PostMapping("/getchapters")
	public List<String> getChaptersList(@RequestBody Course course) {
		System.out.println("id:" + course.getCourseId());
		return instructorService.getChaptersListByCourseID(course.getCourseId());
	}

	@PostMapping("/subtdata/{sId}")
	public List<SubTopicData> getSubtsInCourseBySID(@PathVariable Long sId) {

		List<SubTopicData> stopicList = new ArrayList<SubTopicData>();

//		List<SubTopic> cl = studentService.getAllSubtListByChaptId(sId);

		List<String> stList = instructorService.getSubtopicListByCourseID(sId);

		for (String sbt : stList) {
////		
			SubTopicData stdata = new SubTopicData();
////		

			// split
			String[] arrOfStr = sbt.split(",");

			String id = arrOfStr[0];
			String tit = arrOfStr[1];
			// String indno = arrOfStr[2];

			stdata.setSubtId(Long.parseLong(id));
			stdata.setSubtTitle(tit);
//		stdata.setSubtIndexNo(indno);
////		stdata.setSubtId(sbt.getSubtId());
////		stdata.setSubtId(sbt.getSubtId());
////		
//		System.out.println(sbt.getSubtTitle());
//		stopicList.add(stopicList );
			stopicList.add(stdata);
////		
		}

//		for (String subt : cl) {
//
//			SubTopicData cd = new SubTopicData();
//
//			cd.setSubtId(subt.getSubtId());
//			cd.setSubtTitle(subt.getSubtTitle());
//			
//
//			cList.add(cd);
//
//			
//			
//		}

		return stopicList;

	}

	// get chapters data for perticular course
	@PostMapping("/chaptersdata/{cId}")
	public List<ChaptersData> getChaptersInCourseByCID(@PathVariable Long cId) {

		List<ChaptersData> cList = new ArrayList<ChaptersData>();

		List<Topic> cl = instructorService.getChapListByCourseID(cId);

		for (Topic ch : cl) {

			ChaptersData cd = new ChaptersData();

			cd.setChapterId(ch.getChapterId());
			cd.setChapterTitle(ch.getChapterTitle());
			cd.setChapterIndexNo(ch.getChapterIndexNo());
			cd.setChapterDesc(ch.getChapterDesc());
			cd.setChapterThumbPath(ch.getChapterThumbPath());
			cd.setChapterFilePath(ch.getChapterFilePath());
			cd.setChapterVideoPath(ch.getChapterVideoPath());
			// cd.setCourseId(ch.getChapterId());

			// set subtopics list
			List<SubTopicData> stopicList = new ArrayList<SubTopicData>();

			List<String> stList = instructorService.getSubtopicListByCourseID(ch.getChapterId());

			System.out.println("chapter" + stList.toString());

			for (String sbt : stList) {
////				
				SubTopicData stdata = new SubTopicData();
////				

				// split
				String[] arrOfStr = sbt.split(",");

				String id = arrOfStr[0];
				String tit = arrOfStr[1];
				String indno = arrOfStr[2];

				stdata.setSubtId(Long.parseLong(id));
				stdata.setSubtTitle(tit);
				stdata.setSubtIndexNo(indno);
////				stdata.setSubtId(sbt.getSubtId());
////				stdata.setSubtId(sbt.getSubtId());
////				
//				System.out.println(sbt.getSubtTitle());
//				stopicList.add(stopicList );
				stopicList.add(stdata);
////				
			}

			cd.setSubtpics(stopicList);

			cList.add(cd);

		}

		return cList;

	}

	// get course list category wise
	@PostMapping("student/getcourses/{courseCatId}")
	public List<CourseByCatIdData> getCoursesListByCatID(@PathVariable Long courseCatId) {

		List<CourseByCatIdData> cList = new ArrayList<CourseByCatIdData>();

		List<Course> cl = studentService.getAllCoursesListByCatId(courseCatId);

		for (Course course : cl) {

			CourseByCatIdData cd = new CourseByCatIdData();

			cd.setCourseId(course.getCourseId());
			cd.setCourseTitle(course.getCourseTitle());
			cd.setCourseDesc(course.getCourseDesc());
			cd.setCourseType(course.getCourseType());
			cd.setCoursePrice(course.getCoursePrice());
			cd.setCourseCategory(course.getCourseCategory().getCourseCatId());
			cd.setUser(course.getUser().getUserId());
			cd.setIsDisabled(false);

			cList.add(cd);

		}

		return cList;

	}

	// =================== ADMIN FUNCTIONALITY =========================

//	--------------- Manage Users --------------------------------

	@GetMapping("/admin/users")
	public List<UserData> getAllUsers() {

		List<UserData> userList = new ArrayList<UserData>();

		List<User> uList = registerService.getAllUsersList();

		for (User us : uList) {

			UserData u = new UserData();

			u.setUserId(us.getUserId());
			u.setUserName(us.getUserName());
			u.setFirstName(us.getFirstName());
			u.setLastName(us.getLastName());
			u.setPhoneNo(us.getPhoneNo());
			u.setEmail(us.getEmail());

			userList.add(u);
		}

		return userList;

	}

	@DeleteMapping("admin/deleteuser/{id}")
	public String deleteUser(@PathVariable Long id) {
		registerService.deleteUserByID(id);

		return "User with id " + id + " has been deleted successfully.";
	}

	@GetMapping("/admin/inctruct/count")
	public String getTotalInstrutors() {
		return registerRepository.getUserCount(2);
	}

	@GetMapping("/admin/students/count")
	public String getTotalStudents() {
		return registerRepository.getUserCount(3);
	}

	@GetMapping("/admin/totalcourscnt/")
	public String getTotalCourses() {
		return courseRepository.getCourseCount();
	}

	@GetMapping("/instruct/totalcourses/{uid}")
	public String getTotalCoursesByInstructId(@PathVariable Long uid) {
		System.out.println("in c count");
		return courseRepository.getCourseCountByInstructorId(uid);
	}

	@GetMapping("/instruct/enroll/{uid}")
	public String getTotalEnrollByInstructId(@PathVariable Long uid) {
		return courseRepository.getCourseCountByInstructorId(uid);
	}

//	@GetMapping("/instruct/instruct/earning/{uid}")
//	public String getTotalRevenuByInstructId(@PathVariable Long uid) {
//		System.out.println("in c count");
//		return courseRepository.getCourseCountByInstructorId(uid);
//	}
//	
	@GetMapping("/revenue/{uid}")
	public Float getTotalRevenuByInstructId(@PathVariable Long uid) {
		System.out.println("revenue: " + uid + " " + orderRepository.getTotalSumById(uid));
		return enrollDetailsRepository.findTotalRevenue(uid);
	}

//	--------------- Manage Users End --------------------------------

//	--------------- Manage Course --------------------------------

	@GetMapping("/admin/courses")
	public List<CourseData> getPlantsList() {

		List<CourseData> cList = new ArrayList<CourseData>();

		List<Course> cl = courseRepository.findAll();

		for (Course course : cl) {

			CourseData cd = new CourseData();

			cd.setCourseId(course.getCourseId());
			cd.setCourseTitle(course.getCourseTitle());
			cd.setCourseDesc(course.getCourseDesc());
			cd.setCourseType(course.getCourseType());
			cd.setCoursePrice(course.getCoursePrice());
			cd.setCourseCategory(course.getCourseCategory().getCourseCatId());
			cd.setUser(course.getUser().getUserId());

			cList.add(cd);

		}

		return cList;

	}

	// delete
	@DeleteMapping("/admin/deleteplant/{id}")
	public String deletePlant(@PathVariable Long id) {
		return courseService.deleteCourseByID(id);
	}

	@PutMapping("/admin/approveuser")
	public String setApproveRequest(@RequestBody Aproove approve) {

		aprooveRepository.save(approve);
		return "User approved sucessfully.";
	}

	@GetMapping("/admin/aprovestatus/{uid}")
	public String getApproveStatus(@PathVariable Long uid) {
		return aprooveRepository.findUserStatus(uid);

	}

//	--------------- Manage Course End --------------------------------

	// =========== STUDENT FUNCTIONALITY ============================

	// get enrolled courses
	@PostMapping("/student/enrolled/courses/{userId}")
	public List<EnrolledCourseData> getEnrolledCoursesList(@PathVariable Long userId) {

		List<EnrolledCourseData> cList = new ArrayList<EnrolledCourseData>();

		List<EnrolledCourses> cl = enrolledCoursesService.getEnrollmentByUserID(userId);

		for (EnrolledCourses course : cl) {

			EnrolledCourseData cd = new EnrolledCourseData();

			cd.setEnrollId(course.getEnrollId());
			cd.setCourseTitle(course.getCourseTitle());
			cd.setCourseType(course.getCourseType());
			cd.setuId(course.getuId());
			cd.setcId(course.getcId()); // course id

			cList.add(cd);

		}

		return cList;

	}

	// save course enrollment and email
	@PostMapping("/student/enrollment")
	public String saveCourseEnrollment(@RequestBody EnrolledCourses course) {

		// enroll list of courses
		enrolledCoursesService.saveCourseEnroll(course);

		// send email

		return "Course enrolled successfully";

	}

	// ======================== END =================================

	// --------------- instructor functionality ---------------------

	@PostMapping("/instructor/getapprove")
	public String getApprove(@RequestBody Aproove approve) {

		aprooveRepository.save(approve);
		return "Your request submitted for approval.";
	}

	// get list of records for approval on admin dash
	@GetMapping("/admin/approve/instructor")
	public List<ApproveData> getApproveList() {
		System.out.println("in call");

		List<ApproveData> cList = new ArrayList<ApproveData>();

		List<Aproove> aList = approveService.getApproveListByUserID();

		for (Aproove apr : aList) {

			ApproveData cd = new ApproveData();

			cd.setApvId(apr.getApvId());
			cd.setCerti(apr.getCerti());
			cd.setDesc(apr.getDesc());
			cd.setQualification(apr.getQualification());
			cd.setExp(apr.getExp()); // course id
			cd.setStatus(apr.getStatus()); // course id
			cd.setUiId(apr.getUiId()); // course id

			cList.add(cd);

		}

		return cList;

	}

	// ------------------------- PLACE ORDER ----------------------------------
	@PostMapping("/user/order")
	public String placeOrderDataAndSendMail(@RequestBody OrderData ord) {

		String userId = registerService.getUserIDByUserName(ord.getUserName());
		String userName = ord.getUserName();
		Float total = ord.getTotalAmt();

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());

		Order or = new Order();

		or.setTotalAmt(total);
		or.setUserName(userName);
//		Long uid = Long.parseLong(userId);
		User uss = new User();
		uss.setUserId(Long.parseLong(userId));
		or.setUser(uss);
		or.setOrdDate(date);

		// save order
		orderService.saveOrder(or);

		// list cources
		List<Course> list = ord.getCources();

		String msg = "";

		for (Course el : list) {

			// add list of selected coures to mail
			msg = msg + "" + el.getCourseTitle() + "\t\t" + el.getCoursePrice() + "\n";

			// save db

			// save enrolled course
			// enrolledCoursesService.saveCourseEnroll(null);

			// save order
			EnrolledCourses ec = new EnrolledCourses();

			ec.setCourseTitle(el.getCourseTitle());
			ec.setcId(el.getCourseId());
			ec.setCourseType(el.getCourseType());
			ec.setuId(Long.parseLong(userId));

			// save to db
			enrolledCoursesService.saveCourseEnroll(ec);

			// save to enroll details
			EnrollDetails ed = new EnrollDetails();

			// fetch price by cid
			Float price = courseRepository.getPriceCourse(el.getCourseId());
			ed.setPrice(price);
			ed.setcId(el.getCourseId());

			// fetch instru id for this course
			Long instId = courseRepository.getInstIdCourseId(el.getCourseId());
			ed.setUserId(instId);
			
			System.out.println("Enroll details"+ed.toString());

			enrollDetailsRepository.save(ed);
		}

		// send email functionality

		String toEmail = registerService.getUserEmail(userName);
		String fName = registerService.getUserFirstName(userName);
		String lName = registerService.getUserLastName(userName);
		String clientFullName = fName + lName;
		String subject = clientFullName + "-Order(#527)" + userId;
		String billing = "\n------------------------------------------\n" + "Total : " + total;
		String header = "Hello," + "\n\n" + "Your Order Detals" + "\n\n" + "Course Name" + "\t" + "Price"
				+ "\n--------------------------------------\n";

		String footer = "Thanks & Regards," + "\n" + "From @eLearning";

		String body = header + msg + billing + "\n\n" + footer;

		emailSenderService.sendSimpleEmail(toEmail, body, subject);

		return "Email has been send, please check your mailbox.";

	}

	// ------------------------------------------------------------------------

}
