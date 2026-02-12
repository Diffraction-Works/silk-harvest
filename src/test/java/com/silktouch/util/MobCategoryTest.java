package com.silktouch.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MobCategory enum
 */
class MobCategoryTest {

    @Test
    void testEnumValues() {
        // Verify all enum values exist
        MobCategory[] categories = MobCategory.values();
        assertEquals(4, categories.length);
        assertTrue(contains(categories, MobCategory.COMMON_PASSIVE));
        assertTrue(contains(categories, MobCategory.NEUTRAL_HOSTILE));
        assertTrue(contains(categories, MobCategory.RARE_UTILITY));
        assertTrue(contains(categories, MobCategory.BOSS));
    }

    private boolean contains(MobCategory[] categories, MobCategory target) {
        for (MobCategory category : categories) {
            if (category == target) return true;
        }
        return false;
    }

    @Test
    void testEnumValueOf() {
        assertEquals(MobCategory.COMMON_PASSIVE, MobCategory.valueOf("COMMON_PASSIVE"));
        assertEquals(MobCategory.NEUTRAL_HOSTILE, MobCategory.valueOf("NEUTRAL_HOSTILE"));
        assertEquals(MobCategory.RARE_UTILITY, MobCategory.valueOf("RARE_UTILITY"));
        assertEquals(MobCategory.BOSS, MobCategory.valueOf("BOSS"));
    }

    @Test
    void testEnumOrdinal() {
        // Verify ordinals are consistent
        assertEquals(0, MobCategory.COMMON_PASSIVE.ordinal());
        assertEquals(1, MobCategory.NEUTRAL_HOSTILE.ordinal());
        assertEquals(2, MobCategory.RARE_UTILITY.ordinal());
        assertEquals(3, MobCategory.BOSS.ordinal());
    }
}
