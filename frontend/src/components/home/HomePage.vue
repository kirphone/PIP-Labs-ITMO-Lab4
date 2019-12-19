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
        <tr>
            <td colspan="2">
                <PointsTable/>
            </td>
        </tr>
    </table>
</template>

<script>
    import PointCreateForm from "./PointCreateForm";
    import SvgGrafic from "./SvgGrafic";
    import PointsTable from "./PointsTable";
    export default {
        name: "Home",
        components: {
            PointsTable,
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

</style>