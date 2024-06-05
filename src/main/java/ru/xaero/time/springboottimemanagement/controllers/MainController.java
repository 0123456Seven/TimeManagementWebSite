    package ru.xaero.time.springboottimemanagement.controllers;


    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import ru.xaero.time.springboottimemanagement.models.Application;
    import ru.xaero.time.springboottimemanagement.services.EmailService;


    @Controller
    public class MainController{
        private final EmailService emailService;

        public MainController(EmailService emailService) {
            this.emailService = emailService;
        }

        @GetMapping("/main")
        public String showMain(@ModelAttribute("application") Application application, Model model){
            model.addAttribute("application", application);
            return "main";
        }
        @GetMapping("/services")
        public String showMain(){
            return "services";
        }
        @PostMapping("/submit")
        public String submitForm(@ModelAttribute("application") Application application, Model model) {
            model.addAttribute("firstname", application.getFirstName());
            String to = "mefefef@bk.ru";
            String subject = "New Application Submission";
            String text = "First Name: " + application.getFirstName() + "\n" +
                    "Last Name: " + application.getLastName() + "\n" +
                    "Email: " + application.getEmail() + "\n" +
                    "Phone Number: " + application.getPhoneNumber();

            emailService.sendEmail(to, subject, text);
            return "success";
        }
    }
