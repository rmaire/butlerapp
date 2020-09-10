<template>
    <div class="panel-body">
        <vue-form-generator :schema="schema" :model="model" :options="formOptions"></vue-form-generator>
        <div class="text-right form-group">
            <button class="btn btn-primary btn-sm" id="newparam" v-on:click="add"><span data-feather="plus"></span></button>
        </div>
    </div>
</template>

<script>
    module.exports = {
        store,
        computed: {
            model() {
                return store.state.model;
            },
            schema() {
                return store.state.schema;
            },
            formOptions() {
                return store.state.formOptions;
            }
        },
        mounted: function () {
            store.commit('init');
            Vue.nextTick(function () {
                feather.replace();
                styleForBootstrap();
            });
        },
        updated: function () {
            Vue.nextTick(function () {
                feather.replace();
                styleForBootstrap();
            });
        },
        methods: {
            add(template) {
                console.log("ADD");
                store.commit("add");
            }
        }
    }

    var styleForBootstrap = function () {
        console.log("styleit");
        document.querySelectorAll(".vue-form-generator .form-group").forEach(group => group.className += ' row');
        document.querySelectorAll(".vue-form-generator .form-group").forEach(group => group.style.display += 'flex');
        document.querySelectorAll(".vue-form-generator label").forEach(group => group.className += ' col-sm-2 col-form-label');
        document.querySelectorAll(".vue-form-generator .field-wrap").forEach(group => group.className += ' col-sm-10');
        document.querySelectorAll(".vue-form-generator .hint").forEach(group => group.className += ' col-sm-2');
    }
</script>

<style>
    .dynamicform {
        padding-bottom: 3rem !important;
    }
    #forms-list { margin-top: 3rem; }
</style>
