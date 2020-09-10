<template>
    
    <div id="datatest">
      <form class="command" action="javascript:void(0);">
            <button class="btn btn-primary" v-on:click="saveValue">Run</button>
        </form>
    </div>
</template>

<script>
    module.exports = {
        store,
        mounted: function () {
            feather.replace();
            //store.commit('loadTemplates');
        },
        data: function () {
            return {
                val: {"name":"","value":[{"name":"stringsample","value":"Hello world","type":"StringValue"},{"name":"booleansample","value":true,"type":"BooleanValue"},{"name":"integersample","value":42,"type":"IntegerValue"},{"name":"floatsample","value":42.23,"type":"DoubleValue"},{"name":"listsample","value":[{"name":"","value":"sample1","type":"StringValue"},{"name":"","value":99,"type":"IntegerValue"}],"type":"ListValue"},{"name":"mapsample","value":{"mapsample2":{"name":"sample2","value":99,"type":"IntegerValue"},"mapsample1":{"name":"","value":"sample1","type":"StringValue"}},"type":"MapValue"}],"type":"ListValue"}
            };
        },
        methods: {
            saveValue() {
                store.commit('saveValue', this.val);
                console.log("Save Value");
            }
        }
    }
</script>

<style>
    .yallo {
        background-color: yellow;
    }
</style>