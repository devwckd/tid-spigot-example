package me.devwckd.tid.spigot_example;

import me.devwckd.tid.interpolation.EasingInterpolator;
import me.devwckd.tid.minecraft.interpolation.impl.VectorInterpolationApplier;
import me.devwckd.tid.timeline.Timeline;
import me.devwckd.tid.timeline.TimelineBuilder;
import org.bukkit.util.Vector;

public final class Timelines {

    private Timelines() {
    }

    public static final Timeline SIMPLE_HEAD_ANIM_TIMELINE =
      TimelineBuilder.newBuilder()
        .property(
          "headPos",
          VectorInterpolationApplier.INSTANCE,
          builder -> builder
            .keyframe(0, new Vector(0.0, 1.0, 0.0), EasingInterpolator.EASE_IN)
            .keyframe(20, new Vector(0.0, 2.5, 0.0))
        )
        .doubleProperty(
          "headRotation",
          builder -> builder
            .keyframe(0, 0.0)
            .keyframe(20, 5.0)
        )
        .property(
          "leftParticle",
          VectorInterpolationApplier.INSTANCE,
          builder -> builder
            .keyframe(0, new Vector(0.0, 1.0, 0.0))
            .keyframe(20, new Vector(0.0, 2.5, 1.5))
        )
        .property(
          "rightParticle",
          VectorInterpolationApplier.INSTANCE,
          builder -> builder
            .keyframe(0, new Vector(0.0, 1.0, 0.0))
            .keyframe(20, new Vector(0.0, 2.5, -1.5))
        )
        .build();

}
