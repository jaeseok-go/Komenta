<template>
    <!-- isActive면 active class적용 -->
    <div class="tab-component" :class="{active : isActive}">
        {{tabTitle}}
        <!-- count가 있으면 count표시(count = 23) -->
        <span v-if="count" class="count">
            {{count}}
        </span>
    </div>
</template>

<script>
    export default {
        name: "tab",
        props : ['tabTitle', 'isActive', 'count'],
    }
</script>
