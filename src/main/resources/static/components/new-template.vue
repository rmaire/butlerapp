<template>
    <div class="newtemplate">
        <form action="javascript:void(0);">
            <div class="text-right">
                <button type="submit" class="btn btn-primary btn-sm" id="newtemplate" v-on:click="toggleNewForm"><span data-feather="plus"></span></button>
            </div>
        </form>

        <form id='newtemplateform' action="javascript:void(0);" v-if="visible">
            <h2>New Template</h2>
            <input type="hidden" v-model="template.id">
            <div class="form-group row">
                <label for="newname" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" v-model="template.name">
                </div>
            </div>
            <div class="form-group row">
                <label for="newcode" class="col-sm-2 col-form-label">Template</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="code" v-model="template.code"></textarea>
                </div>
            </div>

            <div class="form-group row" v-for="parameter in template.parameters">
                <label for="newcode" class="col-sm-2 col-form-label">Parameter name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="parameter" v-model="parameter.key">
                </div>
            </div>

            <div class="text-right">
                <button class="btn btn-primary btn-sm" id="newparam" v-on:click="newParam"><span data-feather="plus"></span></button>
            </div>
            <p>
            <div class="text-right">
                <button id="savetemplate" class="btn btn-primary" v-on:click="saveTemplate">Save</button>
            </div>
        </form>
    </div>
</template>

<script>
    module.exports = {
        store,
        data: function () {
            return {
                visible: false,
                template: {id: -1, name: "", code: "", parameters: []}
            };
        },
        mounted: function () {
            feather.replace();
            //store.commit('loadTemplates');
        },
        methods: {
            toggleNewForm() {
                if (this.visible) {
                    this.visible = false;
                } else {
                    this.visible = true;
                    Vue.nextTick(function () {
                        feather.replace();
                    })
                }
            },
            newParam() {
                this.template.parameters.push({key: "", value: ""});
                Vue.nextTick(function () {
                    feather.replace();
                })
            },
            saveTemplate() {
                store.commit('addTemplate', this.template);
                this.toggleNewForm();
            }
        }
    }
</script>

<style>
</style>