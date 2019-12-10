<template>
    <svg width="300" height="300">
        <line x1="0" x2="300" y1="150" y2="150" stroke="black"></line>
        <line x1="150" x2="150" y1="0" y2="300" stroke="black"></line>
        <polyline points="156 10, 150 0, 144 10" stroke="black" fill="none"></polyline>
        <polyline points="290 144, 300 150, 290 156" stroke="black" fill="none"></polyline>

        <!--Длина положительной части оси = 1.2 * R  1.2 * R = 150 => R = 125
            Надпись R отстаёт на 4 px, R/2 на 8px
        -->

        <line x1="212.5" x2="212.5" y1="155" y2="145" stroke="black"></line>
        <line x1="275" x2="275" y1="155" y2="145" stroke="black"></line>

        <line x1="25" x2="25" y1="155" y2="145" stroke="black"></line>
        <line x1="87.5" x2="87.5" y1="155" y2="145" stroke="black"></line>

        <line x1="145" x2="155" y1="87.5" y2="87.5" stroke="black"></line>
        <line x1="145" x2="155" y1="25" y2="25" stroke="black"></line>

        <line x1="145" x2="155" y1="212.5" y2="212.5" stroke="black"></line>
        <line x1="145" x2="155" y1="275" y2="275" stroke="black"></line>

        <text x="204.5" y="140" class="R2">R/2</text>
        <text x="271" y="140" class="R">R</text>

        <text x="19" y="140" class="mR">-R</text>
        <text x="75.5" y="140" class="mR2">-R/2</text>

        <text x="160" y="91.5" class="R2">R/2</text>
        <text x="160" y="29" class="R">R</text>

        <text x="160" y="216.5" class="mR2">-R/2</text>
        <text x="160" y="279" class="mR">-R</text>

        <path d="M 87.5 150 A 62.5 62.5, 90, 0, 0, 150 212.5 L 150 150 Z"
              fill="orange" fill-opacity="0.7" stroke="#4a90e2"></path>

        <polygon points="150 150, 212.5 150, 150 87.5"
                 fill="purple" fill-opacity="0.7" stroke="#4a90e2"></polygon>

        <rect x="87.5" y="25" width="62.5" height="125" fill="yellow" fill-opacity="0.7" stroke="#4a90e2"></rect>
    </svg>
</template>

<script>
    export default {
        name: "SvgGrafic"
    }
</script>

<style scoped>

</style>