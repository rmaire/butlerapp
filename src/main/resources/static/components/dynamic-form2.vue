<template>
    <div id="forms-list">
        <form  class="dynamicform" action="javascript:void(0);" v-for="form in model">
            <h2>{{ form.name }}</h2>
            <!-- input type="hidden" v-bind:value="form.id" -->
            <div class="form-group">
                <div class="row" v-for="val in form.value">

                    <template v-if="val.type == 'StringValue'">
                        <label class="col-sm-2 col-form-label">{{ val.name }}</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="element" v-model="val.value">
                        </div>
                    </template>

                    <template v-if="val.type == 'SecureValue'">
                        <label class="col-sm-2 col-form-label">{{ val.name }}</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="element" v-model="val.value">
                        </div>
                    </template>

                </div>
            </div>

            <div class="form-group">
                <div class="row" v-for="val in form.value">

                    <template v-if="val.type == 'OptionalListValue'">
                        <template v-for="optional in val.value">

                            <template v-if="optional.type == 'StringValue'">
                                <label class="col-sm-2 col-form-label">{{ optional.name }}</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="element" v-model="optional.value">
                                </div>
                            </template>

                            <template v-if="optional.type == 'SecureValue'">
                                <label class="col-sm-2 col-form-label">{{ optional.name }}</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="element" v-model="optional.value">
                                </div>
                            </template>

                            <template v-if="optional.type == 'ListValue'">
                                <label class="col-sm-2 col-form-label">{{ optional.name }}</label>
                                <div class="col-sm-10">
                                    <select class="form-control">
                                        <option v-for="opt in optional.value">{{ opt.value }}</option>
                                    </select>
                                </div>
                            </template>

                        </template>
                    </template>

                    

                </div>
                <div class="text-right form-group">
                        <button class="btn btn-primary btn-sm" id="newparam" v-on:click="newProduct"><span data-feather="plus"></span></button>
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
            model() {
                return store.state.model;
            }
        },
        mounted: function () {
            store.commit('loadModel');
            feather.replace();
        },
        updated: function () {
            feather.replace();
        },
        methods: {
            updateTemplate(template) {
                console.log(template);
                store.commit('updateTemplate', template);
            },
            newProduct() {
                console.log("New Product");
//                store.commit('updateTemplate', template);
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
