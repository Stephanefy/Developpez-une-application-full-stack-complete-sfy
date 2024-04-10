-- Insert users
INSERT INTO `users` (email, password, username) VALUES
('user1@example.com', '$2y$10$.2UcDDirI7nscjlT2/STK.VBrc4GXVBwMkPMS1Ve40E66wWA4togO', 'userone'),
('user2@example.com', '$2y$10$KbvbUyAF20p6iBo29Z/qy.uLCS1TSnpo5cWf51FYBEWXkEY47b306', 'usertwo'),
('user3@example.com', '$2y$10$r2naUo29JtOb9vZa2pGFUOj6P2y29wgSCV/Npu0UbQqbAKOrmDdg2', 'userthree');

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
(1, 'Introduction to Java',
'Java is a versatile and powerful class-based, object-oriented programming language. It is widely used for building enterprise-scale applications, mobile applications, and large systems. Known for its write-once, run-anywhere capability, it enables developers to create modular programs and reusable code.',
'Java programming language stands as a leading platform due to its efficiency, portability, and extensive libraries. It serves as the cornerstone for many types of networked applications, making it an indispensable tool for developers around the globe. This article aims to introduce Java, covering its basic syntax, key features, and applications. Through examples and explanations, readers will gain a foundational understanding of Java and its place in modern software development.',
NOW()),

(2, 'Spring Framework Basics',
'The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications. From its roots as a dependency injection container, it has grown to encompass a wide range of functionality, including web frameworks, security, data access, and more, all aimed at simplifying Java development.',
'This article delves into the core concepts of the Spring Framework, illustrating how it revolutionizes the development of robust Java applications. Through an exploration of Spring’s IoC container, data access frameworks, and transaction management capabilities, readers will discover how to build more efficient, scalable, and manageable Java applications. The piece also highlights Spring’s role in the development of microservices and cloud-native applications, underscoring its adaptability to modern software architecture trends.',
NOW()),

(3, 'Getting Started with Angular',
'Angular is a platform and framework for building client-side single-page web applications using HTML and TypeScript. It provides developers with tools and design patterns to build large, robust applications. Angular emphasizes code quality and testability, which is why it is widely adopted by enterprises.',
'This article serves as a primer to Angular, guiding readers through the setup of their first Angular application, understanding the framework’s core concepts, and exploring its rich ecosystem. From modules and components to services and routing, the piece provides a comprehensive overview of Angular’s capabilities and how it facilitates the development of interactive web applications. By the end, readers will be equipped with the knowledge to embark on their Angular development journey.',
NOW());


-- Assuming the user_theme_subscriptions table exists for User-Theme ManyToMany relationship
-- Subscribe users to themes (adjust user_id and theme_id as needed)
INSERT INTO `user_subscriptions` (user_id, theme_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 4);

-- Assuming the article_themes table exists for Article-Theme ManyToMany relationship
-- Link articles to themes (adjust article_id and theme_id as needed)
INSERT INTO `article_themes` (article_id, theme_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Insert comments (adjust article_id and author_id as needed)
INSERT INTO `comments` ( article_id, author_id, content, publication_date) VALUES
(1, 2, 'Great introduction to Java!', NOW()),
(2, 1, 'I love Spring Framework!', NOW()),
(3, 3, 'Angular has been my go-to for web development.', NOW());
