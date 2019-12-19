<template>
    <table columns="2" width="100%">
        <tr>
            <td>
                <point-create-form/>
            </td>
            <td>
                <SvgGrafic/>
            </td>
        </tr>
    </table>
</template>

<script>
    import PointCreateForm from "./PointCreateForm";
    import SvgGrafic from "./SvgGrafic";
    export default {
        name: "Home",
        components: {
            SvgGrafic,
            PointCreateForm
        },
        computed: {
            token: function () {
                return this.$store.state.token;
            }
        }
    }
</script>

<style scoped>
#form{
    float: left;
}

#svg{
    float: right;
}
</style>