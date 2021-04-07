/*
 * Copyright <2017> <Blekinge Tekniska Högskola>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE.
 */

package se.bth.serl.clony.chunks;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Michael Unterkalmsteiner
 *
 */
public abstract class BaseChunkCollection {
	protected SortedSet<Clone> clones;
	
	public BaseChunkCollection() {
		clones = new TreeSet<>();
	}
	
	public abstract void addChunk(Chunk c);
	public abstract List<Chunk> getChunks();
	public abstract SortedSet<Clone> getClones();
	
	public abstract boolean isEmpty();
	
	//TODO implement expansion. HINT: think recursively
	protected int expand(List<Chunk> a, List<Chunk> b) {
		for(int i = 0; i<b.size(); i++) {
			if (a.size() > 0 & b.size() > 0) {// Finns det n�got kvar att j�mf�ra?
				if (a.get(i).getChunkContent() == b.get(i).getChunkContent()) {//	AND inneh�llet i f�rsta Chunken i a EQUALS inneh�llet i f�rsta Chunken i b
					
					return 1; //saknar koden h�r
					
					//--F�rs�k utan att kalla expand inom sig sj�lv
					
				    //int aSubList = a.subList(i, -1).length;		
							
					//return 1 + expand(a.subList(i, -1),b.subList(i, -1));
					//return 1 + expand(a[-i:], b[-i:]);
				}
			}
			else {
				return 0;
			}
		}
		
			
		
		
	
	//	RETURN 1 + expand(resten av a, resten av b)
    // fanns inget att j�mf�ra, eller s� var Chunkarna inte l�ngre likadana.

	}
}
