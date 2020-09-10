<template>
    <div id="forms-list">
        <form  class="dynamicform" action="javascript:void(0);" v-for="form in forms">
            <h2>{{ form.name }}</h2>
            <input type="hidden" v-bind:value="form.id">
            <div class="form-group row" v-for="element in form.elements">
                <input type="hidden" v-bind:value="element.type">
                <label class="col-sm-2 col-form-label">{{ element.name}}</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="element" v-model="element.value">
                </div>
            </div>
            
           <div class="text-right">
                <button class="btn btn-primary" v-on:click="updateTemplate(template)">Save</button>
            </div>
        </form>
    </div>
</template>

<script>
    module.exports = {
        store,
        computed: {
            forms() {
                return store.state.forms;
            }
        },
        mounted: function () {
            feather.replace();
            store.commit('loadForms');
        },
        methods: {
            updateTemplate(template) {
                console.log(template);
                store.commit('updateTemplate', template);
            }
        }
    }
</script>

<style>
    .dynamicform {
        padding-bottom: 3rem !important;
    }
    #forms-list { margin-top: 3rem; }
</style>
