package org.jenkinsci.plugins.workflow.graphanalysis;

import org.jenkinsci.plugins.workflow.graph.FlowNode;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Used to define the start and end of a {@link FlowChunk} to split a {@link org.jenkinsci.plugins.workflow.flow.FlowExecution}
 * (For use with a {@link SimpleChunkVisitor} in the {@link ForkScanner#visitSimpleChunks(SimpleChunkVisitor, ChunkFinder)}
 * @author Sam Van Oort
 */
public interface ChunkFinder {

    /** If true, a chunk is implicitly created whenever we begin */
    boolean isStartInsideChunk();

    /**
     * Test if the current node is the start of a new chunk (inclusive)
     * @param current Node to test for being a start, it will begin the chunk and be included
     * @param previous Previous node, to use in testing chunk
     * @return True if current node is the beginning of chunk
     */
    boolean isChunkStart(@Nonnull FlowNode current, @CheckForNull FlowNode previous);

    /**
     * Test if the current node is the end of a chunk (inclusive)
     * @param current Node to test for being end
     *    <p> For a block, the {@link org.jenkinsci.plugins.workflow.graph.BlockEndNode}
     *    <p> For a legacy stage or marker, this will be first node of new stage (previous is the marker)
     * @param previous Previous node, to use in testing chunk
     * @return True if current is the end of a chunk (inclusive)
     */
    boolean isChunkEnd(@Nonnull FlowNode current, @CheckForNull FlowNode previous);
}
