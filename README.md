# Skill_Sphere 
Grow yourself with the future of E-Learning!

<!-- ABOUT THE PROJECT -->
## About The Project
<!--
<p align="center">
  <img src="https://github.com/user-attachments/assets/3ad948e9-42a3-4974-ab24-b9539dfaf055" alt="Screenshot 2024-08-16 022101" width="700"/>
</p>
 ![Screenshot 2024-08-16 022101](https://github.com/user-attachments/assets/3ad948e9-42a3-4974-ab24-b9539dfaf055)
-->

Skill Sphere is an innovative e-learning platform engineered to make education accessible, flexible, and engaging for learners of all ages and backgrounds. The platform offers a comprehensive range of online courses across diverse domains, leveraging modern web technologies to deliver a seamless and interactive learning experience.

A learning system based on formalised teaching but with the help of electronic resources is known as E-learning. While teaching can be based in or out of the classrooms, the use of computers and the Internet forms the major component of E-learning.

An eLearning project aims to create an engaging and informative online course that helps learners achieve the desired learning outcomes, but this is from the macro perspective. From the micro perspective, an eLearning project involves the effective coordination of your development team to build the course to standard and launch it.

The Administrator of the system has authority to not only add but also he removes instructor or student . The student can login through proposed computer with their Enrolment student id matching the details to the student’s database, then they can watch the lecture

System Features:
1. Registration
2. Login(Roles: Admin, Instructor, Student) <br>
   * local storage use for login<br>
   * forgot password
3. Dashboard(Admin, Instructor, Student)
4. Admin(Dashboard screen) functionalities:<br>
   * user management(student and instructors)<br>
   * view total revenue <br>
   * view total users(instructor and students) count.
5. Instructor(Dashboard screen) functionalities:<br>
   * able to create courses(will have a form)<br>
   * able to upload pdf for a topic<br>
   * able to upload video for a topic<br>
   * able to upload thumbnail for a topic<br>
   * view total balance(earning)
   * student enrolled counter
6. Student(Dashboard screen) functionality<br>
   * register for a free course<br>
   * register(purchase) a paid course<br>
   * needs to be done payment processing for paid course<br>
   * view registered course<br>
   * access video lesson of a registered course<br>
   * access pdf for a registered course<br>
   * course registration mail or order conformation maill

<!--
* Your time should be focused on creating something amazing. A project that solves a problem and helps others
* You shouldn't be doing the same tasks over and over like creating a README from scratch
* You should implement DRY principles to the rest of your life :smile:
Use the `BLANK_README.md` to get started.
-->
<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- GETTING STARTED -->
## Getting Started

This is how you give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

Inorder to Run the Application you must have these softwares on you system:
* STS version 4
* Visual Studio Code

### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

<!--1. Get a free API Key at [https://example.com](https://example.com)-->
1. Clone the Repository:
   ```sh
   git clone https://github.com/shubhamrokade277/Skill_Sphere.git
   ```
2. Open Backend in STS 4
3. Force update the maven project
   ```sh
   ElearningBackend > Maven > Update Project > Force Update
   ```
5. Change SQL credentials in `Application.properties`
   ```sh
   spring.datasource.username=(Your Username)
   spring.datasource.password=(Your Password)
   ```
7. Run as Spring Boot Application
   ```sh
   ElearningBackend > Run as > Spring Boot App
   ```

9. Open Frontend in VS Code
10. Install NPM packages:
   ```sh
   npm i --legacy-peer-deps
   ```
11. Start the React App:
   ```sh
   npm start
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Top contributors:

<a href="https://github.com/missionaug24/Missions-SkillSphere-E-learningHub/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=missionaug24/Missions-SkillSphere-E-learningHub" />
</a>
<br><br>
<!--
Made with [contrib.rocks](https://contrib.rocks).
<a href="https://github.com/othneildrew/Best-README-Template/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=othneildrew/Best-README-Template" alt="contrib.rocks image" />
</a>
-->

<!--
<p align="right">(<a href="#readme-top">back to top</a>)</p>
-->

<!-- LICENSE -->
## License

See `LICENSE.txt` for more information.
<br><br>

<!--
<p align="right">(<a href="#readme-top">back to top</a>)</p>
-->

<!-- CONTACT -->
## Contact

Aniket Goraksha Khatal: <br>
[Linked-In](https://www.linkedin.com/in/aniket-khatal/) <br>
[Link-Tree](https://linktr.ee/AniketKhatal)
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
<!--
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
-->


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
<!--
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com
-->
