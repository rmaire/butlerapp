Vue.use(Vuex);
Vue.use(VueRouter);

const store = new Vuex.Store({
    state: {
        form: [],
        model: []
    },
    mutations: {
        loadModel(state) {
            axios.get("/atlas/model")
                    .then(function (response) {
                        console.log("============ loadForms() ============");
                        console.log(response.data);
                        while (state.model.length > 0) {
                            state.model.pop();
                        }

                        for (var model of response.data) {
                            state.model.push(model);
                        }

                        for (var form of response.data) {
                            console.log(form);
                            var cleanedResponseData = removeOptionalType(form, "OptionalListValue");
                            console.log(cleanedResponseData);
                        }


                    })
                    .catch(err => {
                        console.log('Error:');
                        console.log(err)
                    })
        }
    }
});

function removeOptionalType(o) {
//    console.log(o);
    for (var obj of o.value) {
        obj.value = obj.value.filter(el => {
            if (el.children) {
                removeType(el, filter);//delete subnodes
            }
            return el.type !== "OptionalListValue"; //delete this
        });
    }
}

new Vue({
    el: '#dynamic-form2',
    components: {
        'dynamic-form2': httpVueLoader('/components/dynamic-form2.vue')
    }
});

//new Vue({
//    el: '#new-template',
//    components: {
//        'new-template': httpVueLoader('/components/new-template.vue')
//    }
//});

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

//new Vue({
//    el: '#values-test',
//    components: {
//        'values-test': httpVueLoader('/components/values-test.vue')
//    }
//});

//new Vue({
//    el: '#simple-test',
//    components: {
//        'simple-test': httpVueLoader('/components/simpletest.vue')
//    }
//});