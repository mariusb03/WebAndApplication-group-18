package webApp.tool;

import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import webApp.model.Courses;
import webApp.model.Providers;
import webApp.model.Topics;
import webApp.model.Users;
import webApp.repository.CoursesRepository;
import webApp.repository.ProvidersRepository;
import webApp.repository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webApp.repository.UserRepository;

/**
 * class that Initializer the database with existing courses.
 */
@Component
public class CoursesInitializer implements ApplicationListener<ApplicationReadyEvent> {
  @Autowired
  private CoursesRepository coursesRepository;
  @Autowired
  private ProvidersRepository providersRepository;
  @Autowired
  private TopicRepository topicRepository;
  @Autowired
  private UserRepository userRepository;

  private final Logger logger = LoggerFactory.getLogger("create a preset of courses");

  /**
   * a method to create a present of courses.
   */
  @Override
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    if (coursesRepository.count() > 0) {
      logger.info("Courses already initialized. Skipping initialization.");
      return;
    }
    initializeCourses();
    initializeTopics();
    initializeProviders();
    initializeUsers();
    initializeCourseTopicRelations();
    initializeCourseProviderRelations();
  }

  /**
   * files the database with providers that are already created.
   */
  private void initializeProviders() {
    logger.info("Initializing providers to database...");

    Providers ntnu = new Providers(1, "NTNU");
    Providers asf = new Providers(2, "ASF");
    Providers pearson = new Providers(3, "Person");
    Providers oracle = new Providers(4, "Oracle");
    Providers microsoft = new Providers(5, "Microsoft");
    Providers amazon = new Providers(6, "Amazon");
    Providers adobe = new Providers(7, "Adobe");
    Providers apple = new Providers(8, "Apple");
    Providers google = new Providers(9, "Google");
    Providers bi = new Providers(10, "BI");
    Providers uio = new Providers(11, "UIO");
    Providers uib = new Providers(12, "UIB");

    providersRepository.saveAll(List.of(
        ntnu, asf, pearson, oracle, microsoft, amazon, adobe, apple, google, bi, uio, uib
    ));

    logger.info("provider initialized to database");
  }

  /**
   * files the database with topics that are already created.
   */
  private void initializeTopics() {
    logger.info("Initializing topics to database...");

    Topics java = new Topics(1, "Java");
    Topics realTimeProgramming = new Topics(2, "real-time programming");
    Topics multiThreading = new Topics(3, "multi-threading");
    Topics sql = new Topics(4, "SQL");
    Topics relationalDatabases = new Topics(5, "relational databases");
    Topics mySql = new Topics(6, "MySQL");
    Topics web = new Topics(7, "web");
    Topics dotNet = new Topics(8, ".net");
    Topics csharp = new Topics(9, "C#");
    Topics azure = new Topics(10, "Azure");
    Topics cloudServices = new Topics(11, "cloud services");
    Topics administration = new Topics(12, "administration");
    Topics aws = new Topics(13, "AWS");
    Topics keywordResearch = new Topics(14, "keyword research and analysis");
    Topics technicalSeo = new Topics(15, "technical SEO optimization");
    Topics offPageSeo = new Topics(16, "off-page SEO strategies");
    Topics advancedAnalytics = new Topics(17, "advanced analytics and reporting");
    Topics strategicStorytelling = new Topics(18, "strategic storytelling");
    Topics targetedEngagement = new Topics(19, "targeted engagement techniques");
    Topics dataDrivenOptimization = new Topics(20, "data-driven optimization");
    Topics imageProcessing = new Topics(21, "image processing");
    Topics python = new Topics(22, "Python");
    Topics machineLearning = new Topics(23, "machine learning");
    Topics programming = new Topics(24, "programming");
    Topics dataScience = new Topics(25, "data science");
    Topics neuralNetworks = new Topics(26, "neural networks");
    Topics databricks = new Topics(27, "Databricks");

    topicRepository.saveAll(List.of(
        java, realTimeProgramming, multiThreading, sql, relationalDatabases, mySql, web, dotNet,
        csharp, azure, cloudServices, administration, aws, keywordResearch, technicalSeo,
        offPageSeo, advancedAnalytics, strategicStorytelling, targetedEngagement,
        dataDrivenOptimization, imageProcessing, python, machineLearning, programming, dataScience,
        neuralNetworks, databricks
    ));

    logger.info("Topics initialized to database");
  }

  /**
   * initializes the user relations in the database.
   */
  public void initializeUsers() {
    logger.info("Initializing users to database...");

    Users regularUser = new Users("Dave", "dave@davidson.no",
        "Dangerous2024", "user");
    Users admin = new Users("Chuck", "world@chuck.norris",
        "Nunchucks2024", "admin");

    userRepository.saveAll(List.of(
        admin, regularUser
    ));

    logger.info("Users initialized to database");
  }

  /**
   * files the database with courses that are already created.
   */
  public void initializeCourses() {
    logger.info("Initializing courses to database...");

    Courses realTimeProgrammingJava = new Courses("Real-Time Programming in Java",
        "Expert", "June 3 rd – June 28th", 7.5, 40,
        "Java SE 17 Programmer Professional", 61999,
        "Embark on a transformative learning experience with our expert-level online "
            + "course, \"RealTime Programming in Java.\" Designed for seasoned developers and Java"
            + " enthusiasts seeking mastery in real-time applications, this advanced course delves"
            + " deep into the intricacies of leveraging Java for mission-critical systems. Explore"
            + " cutting-edge concepts such as multithreading, synchronization, and low-latency "
            + "programming, equipping you with the skills needed to build responsive and robust "
            + "real-time solutions. Led by industry experts with extensive hands-on experience, "
            + "this course combines theoretical insights with practical application, ensuring you "
            + "not only grasp the theoretical underpinnings but also gain the proficiency to "
            + "implement real-time solutions confidently. Elevate your Java programming expertise "
            + "to new heights and stay ahead in the ever-evolving landscape of real-time systems "
            + "with our comprehensive and immersive course.",
        "Information Technology Courses");
    Courses sql = new Courses("Introduction to SQL Essentials", "Beginner",
        "June 10th – June 28th", 2, 20,
        "SQL Fundamentals", 28322,
        "Dive into the fundamentals of database management with our beginner-level "
            + "online course, \"Introduction to SQL Essentials.\" Geared towards those new to the"
            + " world of databases and SQL, this course provides a comprehensive foundation for "
            + "understanding and utilizing SQL for effective data management. While MySQL is "
            + "touched upon to broaden your practical knowledge, the core focus is on SQL's "
            + "universal principles applicable across various database systems. Led by seasoned "
            + "instructors, the course covers database design, querying data, and basic data "
            + "manipulation using SQL commands. With a hands-on approach, you'll engage in "
            + "practical exercises to reinforce your learning, ensuring you gain the skills "
            + "necessary to navigate and interact with databases confidently. Whether you're a "
            + "budding developer, analyst, or anyone eager to harness the power of databases, "
            + "this course offers an accessible entry point into the world of SQL, setting the "
            + "stage for your future success in data-driven environments.",
        "Information Technology Courses");
    Courses webAppWithDotNet = new Courses("Creating Web Application with .Net",
        "Beginner", "Aug 5^th – Aug 16^th", 4, 40,
        ".Net Developer Fundamentals", 8156,
        "Embark on your journey into web development with our beginner-level online "
            + "course, \"Creating Web Applications with .NET.\" Tailored for those stepping into "
            + "the dynamic world of web development, this course provides a solid foundation in "
            + "utilizing the versatile .NET framework to build powerful and interactive web "
            + "applications. Guided by experienced instructors, you'll explore fundamental "
            + "concepts such as web application architecture, user interface design, and "
            + "server-side scripting using .NET technologies like ASP.NET. Throughout the "
            + "course, you'll engage in hands-on projects that walk you through the entire "
            + "development process, from designing responsive user interfaces to implementing "
            + "server-side functionality. Gain practical skills in C# programming and discover "
            + "how to leverage the robust features of .NET to bring your web applications to life."
            + " Whether you're a programming novice or transitioning from another language, this "
            + "course offers a welcoming entry point into the exciting realm of web application "
            + "development with .NET, setting you on a path to create dynamic and engaging online "
            + "experiences.",
         "Information Technology Courses");
    Courses azureFundamentals = new Courses("Azure Fundamentals", "Beginner",
        "Aug 5th – Aug 30th", 2, 10,
        "AZ-900 Azure Fundamentals", 6114,
        "Embark on your cloud computing journey with our beginner-level online course, "
            + "\"Azure Fundamentals,\" meticulously crafted to prepare you for the AZ-900 exam. "
            + "Whether you're new to cloud technologies or seeking to validate your foundational "
            + "knowledge, this course provides a comprehensive introduction to Microsoft Azure, "
            + "one of the industry's leading cloud platforms. Delve into the essentials of cloud "
            + "concepts, Azure services, pricing, and compliance, all while guided by expert "
            + "instructors who understand the importance of building a strong cloud foundation. "
            + "Through a combination of engaging lectures, hands-on labs, and real-world "
            + "scenarios, you'll gain practical insights into deploying solutions on Azure and "
            + "mastering fundamental cloud principles. By the end of the course, you'll not only "
            + "be wellprepared to ace the AZ-900 exam but will also have a solid understanding "
            + "of Azure's capabilities, empowering you to confidently navigate the vast landscape "
            + "of cloud computing. Join us on this educational journey and unlock the potential "
            + "of cloud technology with Azure Fundamentals.",
        "Information Technology Courses");
    Courses azureAdministration = new Courses("Azure Administration",
        "Intermediate", "Sep 2nd – Dec 20th", 4, 5,
        "AZ-104 Microsoft Certified Cloud Administrator", 12228,
         "Elevate your cloud expertise with our intermediate-level online course,"
             + " \"Azure Administrator,\" meticulously designed to prepare you for the AZ-104 "
             + "exam – your gateway to becoming a Microsoft Certified Cloud Administrator. "
             + "Tailored for individuals with a foundational understanding of Azure, this course "
             + "takes a deep dive into advanced administration and management tasks, honing "
             + "the skills required for efficient cloud operations. Led by seasoned Azure "
             + "professionals, you'll explore intricate topics such as virtual networking, "
             + "identity management, and governance strategies, gaining hands-on experience "
             + "through practical exercises and real-world scenarios. The course's comprehensive "
             + "coverage aligns seamlessly with the AZ-104 exam objectives, ensuring that you "
             + "not only pass the certification but emerge as a proficient Azure Administrator "
             + "capable of implementing robust cloud solutions. Whether you're looking to enhance "
             + "your career or solidify your position as a cloud expert, this course is your key "
             + "to mastering the intricacies of Azure administration and achieving Microsoft "
             + "Certified Cloud Administrator status. Join us on this transformative journey "
             + "towards advanced Azure proficiency.",
        "Information Technology Courses");
    Courses awsCloudPractitioner = new Courses("AWS Cloud Practitioner",
        "Beginner", "September 9th – September 20th", 2, 20,
        "CLF-C02 AWS Certified Cloud Practitioner", 4173,
        "Discover the fundamentals of cloud computing in our beginner-level online "
            + "course, \"AWS Cloud Practitioner,\" designed to prepare you for the CLF-C02 "
            + "certification exam. Tailored for individuals with minimal prior experience in "
            + "cloud technologies, this course provides a robust foundation in understanding "
            + "the essential concepts of Amazon Web Services (AWS). Led by experienced AWS "
            + "professionals, the course delves into core topics, including cloud architecture, "
            + "AWS services, security, and pricing models. Through dynamic lectures and hands-on "
            + "labs, you'll gain practical insights into navigating the AWS console, setting up "
            + "basic infrastructure, and comprehending key cloud principles. By the course's end, "
            + "you'll be well-equipped to excel in the CLF-C02 exam and possess a foundational "
            + "understanding of AWS, empowering you to confidently explore and leverage cloud "
            + "services. Join us in this educational journey, and initiate your AWS Cloud "
            + "Practitioner certification with assurance and proficiency.",
        "Information Technology Courses");
    Courses seo = new Courses("Search Engine Optimization", "Intermediate",
        "Aug 5th – Auth 30th", 2, 4, "SEO Wizard",
        340124,
        "Deepen your expertise in the digital landscape with our intermediate-level "
            + "online course, \"Search Engine Optimization (SEO).\" Tailored for marketers, "
            + "business owners, and digital enthusiasts looking to refine their online presence, "
            + "this course takes a comprehensive dive into the intricacies of SEO strategies and "
            + "techniques. Led by seasoned SEO professionals, the course covers advanced topics "
            + "such as keyword research, on-page and off-page optimization, technical SEO, and "
            + "analytics. Through a blend of theoretical insights and practical exercises, you'll "
            + "learn how to enhance website visibility, improve search engine rankings, and drive "
            + "organic traffic effectively. Stay ahead in the ever-evolving digital landscape by "
            + "mastering the art and science of SEO. Whether you're aiming to boost your "
            + "business's online visibility or embark on a career in digital marketing, this "
            + "course equips you with the skills and knowledge needed to navigate the complexities"
            + " of SEO with confidence and success. Join us and elevate your digital presence with"
            + " our intermediate-level SEO course",
        "Digital Marketing Courses");
    Courses socialMediaMarketing = new Courses("Social Media Marketing",
        "Intermediate", "Aug 5th – Auth 30th", 2, 4,
        "Certified Social Alchemist", 340124,
        "Elevate your digital marketing prowess with our intermediate-level online "
            + "course, \"Social Media Marketing.\" Tailored for marketers, business professionals,"
            + " and enthusiasts seeking to harness the power of social platforms, this course "
            + "provides an in-depth exploration of advanced social media marketing strategies. "
            + "Led by industry experts, you'll delve into nuanced topics such as audience "
            + "targeting, content optimization, social media advertising, and analytics. Through "
            + "a blend of theoretical insights and hands-on exercises, you'll gain practical "
            + "skills to create compelling social media campaigns, foster audience engagement, "
            + "and measure the impact of your efforts. Stay at the forefront of digital marketing "
            + "trends by mastering the art of crafting impactful narratives, building brand "
            + "loyalty, and leveraging diverse social channels. Whether you aim to enhance your "
            + "business's online presence or advance your career in digital marketing, this "
            + "course equips you with the tools and knowledge to navigate the dynamic landscape "
            + "of social media marketing with confidence and proficiency. Join us and amplify "
            + "your social media marketing expertise with our intermediate-level course.",
        "Digital Marketing Courses");
    Courses businessStrategy = new Courses("Business Strategy", "Beginner",
        "June 3rd – November 29th", 10, 10,
        "Certified Strategic Business Architect (CSBA)", 15000,
        "Master the art of strategic thinking with our expert-level online course, "
            + "\"Business Strategy.\" Tailored for seasoned professionals, entrepreneurs, and "
            + "strategic leaders, this course offers an immersive exploration of advanced business"
            + " strategy concepts and applications. Led by industry thought leaders, you'll delve "
            + "into intricate topics such as competitive analysis, market positioning, disruptive "
            + "innovation, and global strategic management. Through case studies, simulations, "
            + "and real-world scenarios, you'll sharpen your ability to make informed strategic "
            + "decisions that drive long-term success. This course goes beyond the basics, "
            + "challenging you to synthesize diverse business elements into a cohesive and "
            + "forward-thinking strategy. Whether you aspire to lead a multinational corporation "
            + "or refine your entrepreneurial ventures, our expert-level Business Strategy course "
            + "empowers you to navigate complex business landscapes with foresight and precision. "
            + "Join us in this transformative learning journey and elevate your strategic acumen "
            + "to new heights.",
        "Business and Entrepreneurship Courses");
    Courses mlBasicsPython = new Courses("Machine Learning Basics with Python",
        "Beginner", "Aug 19th – Augh 30th", 2, 10,
        "Machine Learning Fundamentals", 60000,
        "Embark on your journey into the exciting realm of artificial intelligence "
            + "with our beginnerlevel online course, \"Machine Learning Basics with Python.\" "
            + "Tailored for individuals new to the world of machine learning, this course "
            + "provides a comprehensive introduction to the fundamental concepts and techniques "
            + "using the versatile Python programming language. Led by experienced instructors, "
            + "you'll explore the basics of supervised and unsupervised learning, delve into "
            + "popular machine learning algorithms, and gain hands-on experience through practical"
            + " exercises. No prior coding experience is required, making this course an ideal "
            + "starting point for beginners eager to grasp the essentials of machine learning. "
            + "By the end of the course, you'll have a solid foundation in using Python for "
            + "machine learning applications, empowering you to unravel the mysteries of data and "
            + "embark on a fascinating journey into the world of intelligent algorithms. Join us"
            + " and demystify the basics of machine learning with Python in this accessible and "
            + "empowering course.",
        "Data Science and Analytics Course");
    Courses imageRecognition = new Courses("Image Recognition",
        "Intermediate", "Sep 2nd – Sep 27th", 4, 20,
        "Machine Vision Associate", 90000,
        "Deepen your expertise in the realm of artificial intelligence with our "
            + "intermediate-level online course, \"Image Recognition with Python.\" Tailored for "
            + "those with a foundational understanding of machine learning, this course immerses "
            + "you in the intricacies of image recognition techniques and technologies using the "
            + "powerful Python programming language. Led by seasoned instructors, you'll explore "
            + "advanced concepts such as convolutional neural networks (CNNs), image "
            + "preprocessing, and transfer learning. Through hands-on projects and real-world "
            + "applications, you'll sharpen your skills in training models to recognize and "
            + "classify images with precision. This course is ideal for individuals looking to "
            + "expand their knowledge in computer vision and image processing, and it serves as "
            + "a stepping stone for professionals aspiring to integrate image recognition "
            + "capabilities into their projects. Join us in this intermediate-level course, "
            + "and unlock the potential of image recognition with Python, advancing your "
            + "proficiency in the exciting field of artificial intelligence",
        "Data Science and Analytics Course");
    Courses databricksFundamentals = new Courses("Databricks Fundamentals",
        "Beginner", "Aug 19th – Augh 30th", 2, 10,
        "Databricks Practitioner", 60000,
        "Embark on your data journey with our beginner-level online course,"
            + " \"Databricks Fundamentals.\" Designed for individuals new to the world of big "
            + "data and analytics, this course offers a comprehensive introduction to the "
            + "essential concepts of Databricks, a leading unified analytics platform. Led by "
            + "experienced instructors, you'll navigate through the fundamentals of data "
            + "exploration, data engineering, and collaborative data science using Databricks. "
            + "No prior experience with big data technologies is required, making this course "
            + "an ideal starting point for beginners eager to harness the power of Databricks "
            + "for streamlined data processing and analysis. By the end of the course, you'll "
            + "have a solid foundation in using Databricks to uncover insights from large "
            + "datasets, setting you on a path towards mastering the intricacies of modern data "
            + "analytics. Join us and demystify the fundamentals of Databricks in this accessible "
            + "and empowering course.",
        "Data Science and Analytics Course");

    coursesRepository.saveAll(List.of(
        realTimeProgrammingJava, sql, webAppWithDotNet, azureFundamentals, azureAdministration,
        awsCloudPractitioner, seo, socialMediaMarketing, businessStrategy, mlBasicsPython,
        imageRecognition, databricksFundamentals
    ));

    logger.info("Courses initialized to database");
  }

  /**
   * This method initializes the course-topic relations in the database.
   */
  @Transactional
  public void initializeCourseTopicRelations() {
    logger.info("Initializing course-topic relations in the database...");
    Map<Integer, List<Integer>> courseToTopicMap = Map.ofEntries(
        Map.entry(1, List.of(1, 2, 3, 24)),
        Map.entry(2, List.of(4, 5, 6)),
        Map.entry(3, List.of(7, 8, 9, 24)),
        Map.entry(4, List.of(10, 11)),
        Map.entry(5, List.of(10, 11, 12)),
        Map.entry(6, List.of(11, 13)),
        Map.entry(7, List.of(14, 15, 16, 17)),
        Map.entry(8, List.of(18, 19, 20)),
        Map.entry(9, List.of()), // no topics
        Map.entry(10, List.of(22, 23, 24, 25)),
        Map.entry(11, List.of(21, 22, 23, 24, 25, 26)),
        Map.entry(12, List.of(22, 23, 24, 25, 26, 27))
    );

    for (Map.Entry<Integer, List<Integer>> entry : courseToTopicMap.entrySet()) {
      Integer courseId = entry.getKey();
      List<Integer> topicIds = entry.getValue();

      Optional<Courses> courseOpt = coursesRepository.findById(courseId);
      if (courseOpt.isEmpty()) {
        logger.warn("Course ID {} not found", courseId);
        continue;
      }

      Courses course = courseOpt.get();
      Iterable<Topics> topics = topicRepository.findAllById(topicIds);

      course.getTopics().addAll((Collection<? extends Topics>) topics);
      coursesRepository.save(course);
    }

    logger.info("Course-Topics relation initialized to database");
  }

  /**
   * initializes the course-provider relations in the database.
   */
  @Transactional
  public void initializeCourseProviderRelations() {
    logger.info("Initializing course-provider relations in the database...");

    Map<Integer, List<Integer>> courseToTopicMap = Map.ofEntries(
        Map.entry(1, List.of(1, 4)),
        Map.entry(2, List.of(1, 2, 3)),
        Map.entry(3, List.of(3, 4, 5)),
        Map.entry(4, List.of(1, 3, 5)),
        Map.entry(5, List.of(1, 3, 5)),
        Map.entry(6, List.of(1, 3, 6)),
        Map.entry(7, List.of(5, 6, 7, 8, 9)),
        Map.entry(8, List.of(5, 6, 7, 8, 9)),
        Map.entry(9, List.of(1, 5, 10)), // no topics
        Map.entry(10, List.of(1, 11, 12)),
        Map.entry(11, List.of(1, 11, 12)),
        Map.entry(12, List.of(1, 11, 12))
    );

    for (Map.Entry<Integer, List<Integer>> entry : courseToTopicMap.entrySet()) {
      Integer courseId = entry.getKey();
      List<Integer> providerIds = entry.getValue();

      Optional<Courses> courseOpt = coursesRepository.findById(courseId);
      if (courseOpt.isEmpty()) {
        logger.warn("Course ID {} not found", courseId);
        continue;
      }

      Courses course = courseOpt.get();
      Iterable<Providers> providers = providersRepository.findAllById(providerIds);

      course.getProviders().addAll((Collection<? extends Providers>) providers);
      coursesRepository.save(course);
    }

    logger.info("Course-provider relation initialized to database");
  }
}
