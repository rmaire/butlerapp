<template>
    
    <div class="yallo">
        <ol>
            <li v-for="todo in todos">
                {{ todo.obj.title }}
            </li>
        </ol>
    </div>
</template>

<script>
    module.exports = {
        data: function () {
            var results = [];
            axios.get("https://jsonplaceholder.typicode.com/todos/")
                .then(response => {
                    console.log(response.data);
                    for(var i = 0; i<response.data.length; i++) {
                        results.push({obj: response.data[i]});
                    }
                })
                .catch(err => {
                    console.log('Error:');
                    console.log(err)
                })
            return { todos: results }
        }
    }
</script>

<style>
    .yallo {
        background-color: yellow;
    }
</style>