public class VEB{
	
  int universe;
  int min;
  int max;
  VEB summary;
  VEB clusters[];
  
  public VEB(int u){
    universe = u;
    min = -1;
    max = -1;

    if(u <= 2){
      summary = null;
      clusters = null;
    }
    else{
      int clusterNum = (int) Math.ceil(Math.sqrt(u));
      summary = new VEB(clusterNum);
      clusters = new VEB[clusterNum];
      for(int i = 0; i < clusterNum; i++){
        clusters[i] = new VEB(clusterNum);
      }
    }
  }

  private int high(int x){
    return (int) (x / Math.sqrt(universe));
  }

  private int low(int x){
    return (int) (x % Math.sqrt(universe));
  }

  private int index(int i, int j){
    return (int) (i*Math.ceil(Math.sqrt(universe))+j);
  }

  public void insert(int x){
	if(x >= this.universe) {
		throw new IndexOutOfBoundsException();
	}
	else {
		insert(this, x);
	}
 }
  
  private void insert(VEB v, int x){
    if (v.min == -1){
      v.min = x;
      v.max = x;
      return;
    }

    if(x < v.min){
      int temp = v.min;
      v.min = x;
      x = temp;
    }

    if(x > v.max){
      v.max = x;
    }

    if(v.clusters[high(x)].min == -1){
      insert(v.summary, high(x));
    }

    insert(v.clusters[high(x)], low(x));
 }

  public int successor(int x){
    return successor(this, x);
  }

  private int successor(VEB v, int x){
    if(x < v.min){
      return v.min;
    }
    int i = high(x);
    int j;
    if(low(x)<v.clusters[i].max){
      j = successor(v.clusters[i], low(x));
    }
    else{
      i = successor(v.summary, i);
      j = v.clusters[i].min;
    }

    return(index(i,j));
  }

  public int predecessor(int x){
    return predecessor(this, x);
  }

  private int predecessor(VEB v, int x){
    if(x > v.max){
      return v.max;
    }
    int i = high(x);
    int j;
    if(low(x)<v.clusters[i].max){
      j = predecessor(v.clusters[i], low(x));
    }
    else{
      i = predecessor(v.summary, i);
      j = v.clusters[i].max;
    }

    return(index(i,j));
  }

  public void delete(int x){
    delete(this, x);
  }
  private void delete(VEB v, int x){
    int i;
    if (x == v.min){
      i = v.summary.min;
      if(i == -1){
        v.min = -1;
        v.max = -1;
        return;
      }
      v.min = index(i, v.clusters[i].min);
    }
    delete(v.clusters[high(x)], low(x));
    if (v.clusters[high(x)].min == -1){
      delete(v.summary, high(x));
    }
	  if (x == v.max && v.summary.max == -1){
      return;
    }
  	else{
  		i = v.summary.max;
  		v.max = index(i, v.clusters[i].max);
    }
  }
}