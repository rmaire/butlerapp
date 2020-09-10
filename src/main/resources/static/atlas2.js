Vue.use(Vuex);
Vue.use(VueRouter);
Vue.use(VueFormGenerator);

//const store = new Vuex.Store({
//    state: {
//        model: {},
//        schema: {},
//        formOptions: {}
//    },
//    mutations: {
//        init(state) {
//            state.model = {
//                id: 1,
//                name: 'John Doe',
//                password: 'J0hnD03!x4',
//                skills: ['Javascript', 'VueJS'],
//                email: 'john.doe@gmail.com',
//                status: true
//            }
//
//            state.schema = {
//                fields: [
//                    {
//                        type: 'input',
//                        inputType: 'text',
//                        label: 'ID (disabled text field)',
//                        model: 'id',
//                        readonly: true,
//                        disabled: true,
//                        additional: "Testit"
//                    },
//                    {
//                        type: 'input',
//                        inputType: 'text',
//                        label: 'Name',
//                        model: 'name',
//                        placeholder: 'Your name',
//                        featured: true,
//                        required: true,
//                        additional: "Testit"
//                    },
//                    {
//                        type: 'input',
//                        inputType: 'password',
//                        label: 'Password',
//                        model: 'password',
//                        min: 6,
//                        required: true,
//                        hint: 'Minimum 6 characters',
//                        validator: VueFormGenerator.validators.string
//                    },
//                    {
//                        type: 'select',
//                        label: 'Skills',
//                        model: 'skills',
//                        values: ['Javascript', 'VueJS', 'CSS3', 'HTML5']
//                    },
//                    {
//                        type: 'input',
//                        inputType: 'email',
//                        label: 'E-mail',
//                        model: 'email',
//                        placeholder: 'User\'s e-mail address'
//                    },
//                    {
//                        type: 'submit',
//                        label: 'Status',
//                        model: 'status',
//                        buttonText: 'BUTTON',
//                        inputName: 'button',
//                        default: true,
//                        onSubmit: function (model) {
//                            console.log('BUTTON CLICKED');
//                            console.log(model);
//                            console.log('END')
//                        }
//                    }
//                ],
//                optionals: [
//                    {
//                        type: 'input',
//                        inputType: 'text',
//                        label: 'ID (disabled text field)',
//                        model: 'id',
//                        readonly: true,
//                        disabled: true
//                    }
//                ]
//            }
//
//            state.formOptions = {
//                validateAfterLoad: true,
//                validateAfterChanged: true,
//                validateAsync: true
//            }
//        },
//        add: function (state) {
//            state.schema.fields.push(
//                    state.schema.optionals[0]
//                    );
//        }
//    }
//});

const store = new Vuex.Store({
    state: {
        model: {},
        schema: {},
        formOptions: {}
    },
    mutations: {
        async init(state) {
            state.model = {
                id: 1,
                name: 'John Doe',
                password: 'J0hnD03!x4',
                skills: ['Javascript', 'VueJS'],
                email: 'john.doe@gmail.com',
                status: true
            }

            state.schema = {
                fields: [],
                optionals: [
                    {
                        type: 'input',
                        inputType: 'text',
                        label: 'ID (disabled text field)',
                        model: 'id',
                        readonly: true,
                        disabled: true
                    }
                ]
            }

            var schemaPromise = axios.get("/atlas2/schema/");
            var schemaPromiseResult = await schemaPromise;

            while (state.schema.fields.length > 0) {
                state.schema.fields.pop();
            }

            for (var field of schemaPromiseResult.data.fields) {
                state.schema.fields.push(field);
            }

            state.formOptions = {
                validateAfterLoad: true,
                validateAfterChanged: true,
                validateAsync: true
            }
        },
        add: function (state) {
            state.schema.fields.push(
                    state.schema.optionals[0]
                    );
        }
    }
});

new Vue({
    el: '#dynamic-form3',
    components: {
        'dynamic-form3': httpVueLoader('/components/dynamic-form3.vue')
    }
});