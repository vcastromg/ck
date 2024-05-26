package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.result.CKClassResult;
import com.github.mauricioaniche.ck.result.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.Stack;

public class NumberOfMaxNestedBlock implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {
	private Node nodeInfo = new Node(0, 0);
	private Stack<ASTNode> currentNode = new Stack<>();
	private Stack<Boolean> blocks = new Stack<>();
	private Stack<Boolean> nodes = new Stack<>();

	@Override
	public void visit(Block node) {

		// we always do a +1 if we see a block, with the exception of
		// a switch case, as we do the +1 in the block.
		// note that blocks might not always exist, and that's why we check
		// for their existence at every node later on...
		// if they do not exist, we +1 in the node.
		if(currentNode.empty() || !(currentNode.peek() instanceof SwitchCase)) {
			nodeInfo.plusOne();
			blocks.push(true);
		} else {
			blocks.push(false);
		}

		currentNode.push(node);
	}

	@Override
	public void visit(ForStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nodeInfo.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	@Override
	public void visit(EnhancedForStatement node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nodeInfo.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	@Override
	public void visit(DoStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nodeInfo.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	@Override
	public void visit(WhileStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nodeInfo.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}
	}

	@Override
	public void visit(SwitchStatement node) {

		currentNode.push(node);
		nodes.push(true);
		nodeInfo.plusOne();
	}

	@Override
	public void visit(SwitchCase node) {
		currentNode.push(node);
	}


	@Override
	public void visit(CatchClause node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nodeInfo.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	public void visit(IfStatement node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getThenStatement());
		if(!containsBlock) {
			nodeInfo.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}
	}


	@Override
	public void endVisit(Block node) {
		nodeInfo.popBlock(blocks);

		currentNode.pop();
	}

	@Override
	public void endVisit(IfStatement node) {
		nodeInfo.popBlock(nodes);
	}

	private boolean containsBlock(Statement body) {
		return (body instanceof Block);
	}

	@Override
	public void endVisit(CatchClause node) {
		nodeInfo.popBlock(nodes);
	}

	@Override
	public void endVisit(WhileStatement node) {
		nodeInfo.popBlock(nodes);
	}

	@Override
	public void endVisit(DoStatement node) {
		nodeInfo.popBlock(nodes);
	}

	@Override
	public void endVisit(EnhancedForStatement node) {
		nodeInfo.popBlock(nodes);
	}

	@Override
	public void endVisit(ForStatement node) {
		nodeInfo.popBlock(nodes);
	}

	@Override
	public void endVisit(SwitchStatement node) {
		nodeInfo.popBlock(nodes);
	}

	@Override
	public void setResult(CKMethodResult result) {
		// -1 because the method block is considered a block.
		// and we avoid 0, that can happen in case of enums
		result.setMaxNestedBlocks(Math.max(0, (nodeInfo.getMax() - 1)));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.othersResult.setMaxNestedBlocks(Math.max(0, (nodeInfo.getMax()) - 1));
	}
}
