Vue.use(Vuex);
Vue.use(VueRouter);

const store = new Vuex.Store({
    state: {
        codetemplates: [],
        projects: []
    },
    mutations: {
        addTemplate(state, newtemplate) {
            axios.post('/templates', newtemplate)
                    .then(function (response) {
                        state.codetemplates.push(response.data);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
        },
        loadTemplates(state) {
            axios.get("/templates")
                    .then(function (response) {
                        while (state.codetemplates.length > 0) {
                            state.codetemplates.pop();
                        }
                        for (codetemplate of response.data) {
                            state.codetemplates.push(codetemplate);
                        }
                    })
                    .catch(err => {
                        console.log('Error:');
                        console.log(err)
                    })
        },
        loadProjects(state) {
            axios.get("/templates")
                    .then(function (response) {
                        for (codetemplate of response.data) {
                            state.projects.push(codetemplate.name);
                        }
                    })
                    .catch(err => {
                        console.log('Error:');
                        console.log(err)
                    })
        },
        updateTemplate(state, updatedtemplate) {
            axios.put("/templates", updatedtemplate)
                    .then(function (response) {
                        for (var i = 0; i < state.codetemplates.length; i++) {
                            if (state.codetemplates[i].id == response.data.id) {
                                state.codetemplates[i] = response.data;
                            }
                        }
                    })
                    .catch(err => {
                        console.log('Error:');
                        console.log(err)
                    })
        },
        saveValue(state, value) {
            console.log("Store commit: " + value);
            axios.post("/value", value)
                    .then(function (response) {
                        console.log(response);
//                        for (var i = 0; i < state.codetemplates.length; i++) {
//                            if (state.codetemplates[i].id == response.data.id) {
//                                state.codetemplates[i] = response.data;
//                            }
//                        }
                    })
                    .catch(err => {
                        console.log('Error:');
                        console.log(err)
                    })
        }
    }
});

new Vue({
    el: '#new-template',
    components: {
        'new-template': httpVueLoader('/components/new-template.vue')
    }
});

//new Vue({
//    el: '#templates-list',
//    components: {
//        'templates-list': httpVueLoader('/components/templates-list.vue')
//    }
//});

//new Vue({
//    el: '#project-list',
//    components: {
//        'project-list': httpVueLoader('/components/project-list.vue')
//    }
//});

new Vue({
    el: '#values-test',
    components: {
        'values-test': httpVueLoader('/components/values-test.vue')
    }
});

//new Vue({
//    el: '#simple-test',
//    components: {
//        'simple-test': httpVueLoader('/components/simpletest.vue')
//    }
//});