
<template>
    <div class="yallo">
        <ol>
            <li v-for="todo in todos">
                {{ todo.text }}
            </li>
        </ol>
    </div>
</template>

<script>
    module.exports = {
        data: function () {
            return {
                todos: [
                    {text: 'Learn JavaScript'},
                    {text: 'Learn Vue'},
                    {text: 'Build something awesome'}
                ]
            }
        }
    }
</script>

<style>
    .yallo {
        background-color: yellow;
    }
</style>