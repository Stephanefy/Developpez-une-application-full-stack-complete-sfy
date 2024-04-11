-- Insert users
INSERT INTO `users` (email, password, username) VALUES
('user1@example.com', '$2a$10$8Bq6/tTrGlcoY1fjrc2o8O4vMlE6f0UdWTaoNrEd9z.jHFSS.Zblq', 'userone'),
('user2@example.com', '$2a$10$P14rGXWtdQgy7kkQuaTBnO8BUOevtaqk/CO0JGfbdezeQ6LCSgACO', 'usertwo'),
('user3@example.com', '$2a$10$H0/td/KwTKiZtB1MheejXuRVNFnDQSXwd9LLS6Oqdx72mIIkx.yLW', 'userthree');

-- Insert themes
INSERT INTO `themes` (name, description) VALUES
('Java', 'Java is a widely-used, class-based, object-oriented programming language designed for flexibility, allowing developers to write code once and run it anywhere. Its robust architecture and vast ecosystem make it ideal for developing everything from mobile applications to large-scale enterprise systems.'),
('Spring Framework', 'The Spring Framework is an open-source application framework for the Java platform, offering comprehensive infrastructure support for developing Java apps. It simplifies Java development, promotes good design practices, and supports a wide range of application architectures.'),
('Angular', 'Angular is a platform and framework for building single-page client applications using HTML and TypeScript. Developed and maintained by Google, it offers developers an integrated development experience, complete with tools and libraries to create dynamic, efficient web apps.'),
('Web Security', 'Web Security encompasses the strategies, protocols, and tools designed to protect websites and online services against cyber threats and vulnerabilities. This theme covers best practices for securing web applications, including data protection, authentication, and threat mitigation techniques.'),
('Cloud Computing', 'Cloud Computing represents the delivery of computing services—including servers, storage, databases, networking, software, analytics, and intelligence—over the internet ("the cloud") to offer faster innovation, flexible resources, and economies of scale. It covers various service models such as IaaS, PaaS, and SaaS, facilitating businesses to operate more efficiently and scale as needed.'),
('Machine Learning', 'Machine Learning, a subset of artificial intelligence (AI), enables systems to automatically learn and improve from experience without being explicitly programmed. This theme explores algorithms, techniques, and applications of machine learning, from predictive analytics to natural language processing, highlighting how it drives innovation across industries.'),
('Data Structures & Algorithms', 'Data Structures & Algorithms form the foundation of efficient problem-solving in computer science. This theme delves into the organization, management, and storage of data in various structures, alongside the algorithms that manipulate these structures for tasks such as searching, sorting, and optimization, essential for developing effective software solutions.'),
('DevOps Practices', 'DevOps Practices bridge the gap between software development and IT operations, aiming for shorter development cycles, increased deployment frequency, and more dependable releases, in close alignment with business objectives. This theme covers the principles, practices, and tools that facilitate the DevOps culture, including continuous integration, continuous delivery, and automated testing.');


-- Insert articles (adjust author_id as needed based on the users you've inserted)
INSERT INTO `articles` (author_id, title, content, description, publication_date) VALUES
(1, 'Understanding Cloud Computing',
'Cloud computing offers a revolutionary way to access computing resources over the internet...',
'Cloud computing has emerged as a pivotal technology for modern businesses and individuals, allowing for the scalable consumption of computing resources over the internet. This transformative technology enables users to access a vast array of services, from data storage and processing to sophisticated analytics and artificial intelligence, all without the need for significant upfront investment in physical hardware or ongoing maintenance. The core service models of cloud computing—Infrastructure as a Service (IaaS), Platform as a Service (PaaS), and Software as a Service (SaaS)—cater to diverse needs and offer different levels of control and flexibility, making cloud computing a versatile tool for digital transformation. This in-depth exploration of cloud computing will guide readers through its fundamental concepts, the benefits and challenges it presents, and its profound impact on how we develop, deploy, and perceive technology in an increasingly connected world.',
'2024-01-15 10:00:00'),

(3, 'Machine Learning: An Introduction',
'Machine Learning is at the forefront of artificial intelligence, providing systems the ability to automatically learn and improve from experience without being explicitly programmed. This innovative field applies complex algorithms that parse data, learn from it, and then use that learning to make informed decisions. It powers many of today’s advanced applications, from recommendation engines and virtual personal assistants to self-driving cars and predictive analytics.This comprehensive article introduces the fundamentals of machine learning, diving into its various types - supervised learning, where the system learns from a labeled dataset to make predictions; unsupervised learning, which finds hidden patterns or intrinsic structures in input data; and reinforcement learning, a goal-oriented algorithm that learns from the consequences of its actions. Readers will explore key machine learning algorithms, including decision trees, neural networks, and clustering, and understand their applications across different industries. Through practical examples and detailed explanations, this article demystifies machine learning, highlighting its potential to drive innovation, enhance productivity, and solve complex challenges in today’s data-driven world.',
'Machine learning stands as one of the most innovative and influential realms within artificial intelligence, reshaping industries with its capability to analyze large datasets, learn from data, and make predictions or take actions based on insights. This field encompasses a range of algorithms and techniques, such as neural networks for deep learning, decision trees for classification tasks, and reinforcement learning for decision-making processes. This article aims to unravel the complexities of machine learning, offering readers a comprehensive overview of its principles, key technologies, and practical applications. From enhancing customer experiences to driving advances in fields like healthcare and finance, machine learning is a cornerstone of the current technological revolution, offering new ways to solve age-old problems through data-driven insights.',
'2024-02-20 11:30:00'),

(1, 'Basics of Data Structures & Algorithms',
'Understanding data structures and algorithms is essential for writing efficient code and solving computational problems effectively. Data structures organize data in a manner that facilitates operations such as access, insertion, deletion, and traversal, while algorithms are step-by-step procedures for performing tasks or calculations. Together, they form the backbone of computer science and software development.This article delves deep into the world of data structures and algorithms, covering foundational data structures like arrays, which store elements at contiguous memory locations; linked lists, which consist of nodes that together represent a sequence; stacks and queues, which are linear data structures following particular order of operations; and more advanced structures like trees and graphs, which are used to represent hierarchical or networked data. Alongside, it explores algorithms for searching (e.g., binary search), sorting (e.g., quicksort, mergesort), and graph algorithms (e.g., Dijkstra\'s algorithm for finding the shortest path). Through detailed explanations and coding examples, this article aims to equip readers with a solid understanding of data structures and algorithms, enhancing their problem-solving skills and application knowledge for developing efficient and scalable software solutions.',
'The foundation of effective problem-solving in software development lies in a deep understanding of data structures and algorithms. This article delves into the critical role these fundamental concepts play in coding and computational efficiency. Covering a spectrum from basic data structures like arrays and linked lists to complex algorithms for sorting and searching, this comprehensive guide illuminates the importance of algorithmic thinking. By exploring the nuances of binary trees, hash tables, and graph theory, alongside algorithm design principles such as dynamic programming and backtracking, readers will gain the insights needed to enhance their programming proficiency, optimize their code, and tackle complex challenges with innovative solutions.',
'2024-03-25 09:45:00'),

(2, 'The Pillars of DevOps Practices',
'DevOps practices represent a paradigm shift in the traditional software development and IT operations approach, aiming to foster a culture of collaboration and continuous improvement. By integrating development and operations teams, DevOps seeks to shorten the development lifecycle, enhance deployment frequency, and ensure high-quality software releases that are in close alignment with business objectives. This methodology emphasizes automation, continuous integration (CI), continuous delivery (CD), and rapid feedback loops.This in-depth article explores the core pillars of DevOps, including automation, which minimizes manual work and ensures reproducible environments; continuous integration, which involves automatically testing code changes; continuous delivery, which ensures that changes can be deployed to production safely and quickly; monitoring and logging, which provides visibility into the application\'s performance and helps quickly rectify issues. It also covers infrastructure as code, which manages infrastructure using code and automation, making environments easy to replicate and deploy. Through practical insights and examples, readers will gain a comprehensive understanding of DevOps practices, learning how to implement these principles to foster innovation, efficiency, and collaboration in their development workflows.',
'The DevOps methodology represents a significant shift in the traditional software development and IT operations paradigm, emphasizing a culture of collaboration, automation, and continuous improvement. This article explores the foundational pillars of DevOps practices, including the critical roles of continuous integration and continuous delivery in automating the software release process. By examining how DevOps facilitates a more agile, responsive approach to development, this piece highlights the methodology’s impact on operational efficiency, software quality, and organizational culture. Readers will learn about the integration of DevOps tools and practices into existing workflows, the importance of monitoring and feedback loops, and how DevOps is evolving to meet the demands of modern software development challenges.',
'2024-04-10 08:00:00'),

(3, 'Exploring the Fundamentals of Web Security',
'Web security is a critical aspect of software development, aiming to protect web applications and online services from various cyber threats, including data breaches, attacks, and unauthorized access. With the increasing sophistication of cyber-attacks and the sensitive nature of data being processed online, ensuring the confidentiality, integrity, and availability of web applications is paramount. Web security encompasses a wide range of practices, protocols, and tools designed to safeguard data, prevent vulnerabilities, and maintain user trust.This article offers a deep dive into web security principles, covering essential topics such as encryption, which protects data in transit and at rest; authentication and authorization, which ensure that users are who they claim to be and have appropriate access; and common web vulnerabilities like SQL injection and cross-site scripting (XSS). It also examines the role of security headers, secure coding practices, and the importance of regular security assessments. Through detailed explanations and actionable advice, this article equips readers with the knowledge to implement robust security measures, build secure web applications, and stay ahead of emerging security threats.',
'The digital age has ushered in an era of unprecedented connectivity, with web applications becoming an integral part of our daily lives. However, this reliance on web technology also introduces significant security challenges. This article offers an exhaustive exploration of web security fundamentals, addressing the critical need to protect web applications from a multitude of threats. It covers the spectrum of web vulnerabilities, from injection attacks to cross-site scripting, and the defensive strategies required to mitigate these risks. Through an examination of encryption techniques, secure coding practices, and the implementation of comprehensive security policies, readers will acquire a holistic understanding of how to safeguard web applications against cyber threats, ensuring the integrity, confidentiality, and availability of online services in an increasingly hostile digital landscape.',
'2024-05-05 14:30:00');




-- Assuming the user_theme_subscriptions table exists for User-Theme ManyToMany relationship
-- Subscribe users to themes (adjust user_id and theme_id as needed)
-- Subscribe users to new themes
INSERT INTO `user_subscriptions` (user_id, theme_id) VALUES
(1, 5),
(1, 6),
(2, 4),
(2, 5),
(3, 6),
(3, 7);


-- Assuming the article_themes table exists for Article-Theme ManyToMany relationship
-- Link articles to themes (adjust article_id and theme_id as needed)
-- Link the new articles to their respective themes
INSERT INTO `article_themes` (article_id, theme_id) VALUES
(1, 5),
(3, 6),
(5, 7),
(4, 8),
(2, 4),
(2, 6);


-- Insert comments (adjust article_id and author_id as needed)
INSERT INTO `comments` ( article_id, author_id, content, publication_date) VALUES
(1, 2, 'Great introduction to Java!', NOW()),
(2, 1, 'I love Spring Framework!', NOW()),
(3, 3, 'Angular has been my go-to for web development.', NOW());
