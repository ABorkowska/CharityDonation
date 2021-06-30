document.addEventListener("DOMContentLoaded", function() {

    /**
     * Form Select
     */
    class FormSelect {
        constructor ($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init () {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements () {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents () {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function(e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor (form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init () {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events () {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            console.log('kkkkk')
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        submit (e) {
            e.preventDefault();
            const street = document.querySelector("input[name='street']");
            const city = document.querySelector("input[name='city']");
            const postcode = document.querySelector("input[name='postcode']");
            const phone = document.querySelector("input[name='phone']");
            const date = document.querySelector("input[name='date']");
            const time = document.querySelector("input[name='time']");
            const more_info = document.querySelector("textarea[name='more_info']");
            const selectedCategories = document.querySelectorAll("input[name='categories']:checked");
            const quantity = document.querySelector("input[name='bags']");
            const institution = document.querySelectorAll("input[name='organization']:checked");
            const selectedInstitution = Array.from(institution).map(el => el.value)[0]
            const requestObject = {
                categories: Array.from(selectedCategories).map(el => el.value),
                quantity: quantity.value,
                institution: selectedInstitution,
                street: street.value,
                city: city.value,
                postcode: postcode.value,
                phone: phone.value,
                date: date.value,
                time: time.value,
                comment: more_info.value
            }
            console.log(requestObject)
            console.log(e);
            fetch("http://localhost:8080/api/donation/add", {
                method: "POST", body: JSON.stringify(requestObject),
                headers: {
                    'Content-Type': 'application/json'
                },

            }).then(res => res.json())
                .then(data => {
                    console.log(data)
                    window.location.replace("/home"); //redirect
                })

        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm () {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;


            //categories + quantity
            const selectedCategories = document.querySelectorAll("input[name='categories']:checked");
            const quantity = document.querySelector("input[name='bags']");
            const pack = document.querySelector("#packages");
            pack.innerText = `${quantity.value} worki/worków: ${Array.from(selectedCategories).map(el => el.value).join(", ")}`

            //institution
            const selected = document.querySelectorAll("input[name='organization']:checked");
            const institution = document.querySelector("#institution");
            institution.innerText = `Dla fundacji "${Array.from(selected).map(el => el.value)}" w miejscowości `

            //address
            const street = document.querySelector("input[name='street']");
            const city = document.querySelector("input[name='city']");
            const postcode = document.querySelector("input[name='postcode']");
            const phone = document.querySelector("input[name='phone']");
            const date = document.querySelector("input[name='date']");
            const time = document.querySelector("input[name='time']");
            const more_info = document.querySelector("textarea[name='more_info']");

            const address = document.querySelector("#address");
            console.log(address)
            console.log(street)
            address.innerHTML = `<h4>Adres odbioru:</h4>
                            <ul>
                                <li>${street.value}</li>
                                <li>${city.value}</li>
                                <li>${postcode.value}</li>
                                <li>${phone.value}</li>
                            </ul>`
            const pickup = document.querySelector("#time");
            pickup.innerHTML = `<h4>Termin odbioru:</h4>
                            <ul>
                                <li>${date.value}</li>
                                <li>${time.value}</li>
                                <li>${more_info.value}</li>
                            </ul>`

        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});
