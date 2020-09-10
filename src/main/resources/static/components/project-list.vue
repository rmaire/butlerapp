<template>
    <div id="projects">
        <form  class="command">
            <h4>Select command to run</h4>
            <div class="text-left">
                <select class="custom-select custom-select-lg mb-3" v-model="selected" v-on:change="changed">
                    <option disabled value="default">Select a template to run</option>
                    <option v-for="template of codetemplates" v-bind:value="template.id">{{ template.name }}</option>
                </select>
                <div v-for="parameter of selectedtemplate.parameters">
                    <label class="col-sm-2 col-form-label">{{ parameter.key }}</label>
                    <div class="col-sm-10">
                        <input type="input" class="form-control" id="${key}" name="${key}" v-bind:value="parameter.value">
                    </div>
                </div>
            </div>
            <p></p>
            <button class="btn btn-primary">Run</button>
        </form>
    </div>
</template>

<script>
    module.exports = {
        store,
        computed: {
            codetemplates() {
                return store.state.codetemplates;
            }
        },
        data: function () {
            return {
                selected: 'default',
                selectedtemplate: {}
            }
        },
        mounted: function () {
            feather.replace();
            store.commit('loadTemplates');
//            for(p of store.state.codetemplates[0].parameters) {
//                this.actualParameters:.push(p);
//            }
//            console.log(this.codetemplates.pop());
        },
        methods: {
            changed(event) {
//                console.log(event.target.value);
                console.log(this.codetemplate(event.target.value));
                this.selectedtemplate = this.codetemplate(event.target.value);
            },
            codetemplate(id) {
//                console.log(i);
                for (var i = 0; i < store.state.codetemplates.length; i++) {
//                    console.log(store.state.codetemplates[i])
                    if (id == store.state.codetemplates[i].id) {
                        return store.state.codetemplates[i];
                    }
                }
//                return store.state.codetemplates;
            }
        }
    }
</script>

<style>
    /*    .templateform {
            padding-bottom: 3rem !important;
        }
        #templates-list { margin-top: 3rem; }*/
</style>

