<template>
    <div id="templates-list">
        <form  class="templateform" action="javascript:void(0);" v-for="template in codetemplates">
            <h2>{{ template.name }}</h2>
            <input type="hidden" v-bind:value="template.id">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Template</label>
                <div class="col-sm-10">
                    <textarea type="input" class="form-control" v-model="template.code"></textarea>
                </div>
            </div>

            <div class="form-group row" v-for="parameter in template.parameters">
                <label for="newcode" class="col-sm-2 col-form-label">Parameter name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="parameter.key">
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
            codetemplates() {
                return store.state.codetemplates;
            }
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
    .templateform {
        padding-bottom: 3rem !important;
    }
    #templates-list { margin-top: 3rem; }
</style>
