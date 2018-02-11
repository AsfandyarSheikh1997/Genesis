/*
 * The MIT License
 * 
 * Copyright (c) 2018 FuturICT 2.0
 * 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Features.operations;


import Features.operations.actions.IAction;

import Features.operations.actions.EActionType;
import Features.operations.actions.TransactionAction;
import Repository.IRepository;
import Token.IToken;


/**
 *
 * @author Mark C. Ballandies <bmark@ethz.ch>
 */
public class TransactionOperation extends AOperation{

    public TransactionOperation(IRepository repo) {
        super(repo);
    }

    @Override
    public IAction getNextAction() {
        return new TransactionAction();
    }

    @Override
    public boolean write(IToken token, IClaim claim) {
        
        if(claim.getAction().getType().equals(EActionType.TRANSACTION)){
            TransactionAction action = (TransactionAction) claim.getAction();
            return this.repo.transfer(token, action.getFrom(), action.getTo(), action.getValue(), claim.getProof());
        }else{
            return false;
        }
        
        
    }

    @Override
    public boolean isSet() {
        return true;
    }
    
    
    
    
}