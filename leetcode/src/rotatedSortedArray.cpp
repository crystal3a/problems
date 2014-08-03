/*
 * search in a sorted(asending) array which is rotated somewhere, initially in ascending order
 *
 * test case:
 * 5,1,3, x=4, return -1
 * 5,6,1,2,3,  x=5, return 0
 * */
#include "../header/preliminary.h"

int search(int A[], int n, int x){  // problem I: no duplicate
    int v=0, u=n-1;
    while(v <= u){
        int m = (v+u)/2;
        if(x == A[m]){
            return m;
        }else if(A[v] <= A[m]){
            if(x > A[m]){
                v = m+1;
            }else if(x >= A[v]){
                u = m-1;
            }else{
                v = m+1;
            }
        }else{        //A[v] > A[m], now the rotated node is between (v,m)
            if(x < A[m] || x > A[v]){
                u = m-1;
            }else if(x <= A[u]){
                v = m+1;
            }else if(x == A[v]){  //my own added branch
                return v;
            }else{
                printf("here!!!!, now v=%d, u=%d, m=%d\n", v, u, m);
                u = m-1;
            }
        }
    }
    return -1;
}

int searchII(int A[], int n, int key){  // probelm II: with duplicates
    int l = 0, r = n-1;
	while(l <= r){
	    int m = l + (r-l)/2;
		if(A[m] == key)    return m;
		if(A[l] < A[m]){    // left half is sorted
		    if(A[l] <= key && key < A[m]){
			    r = m-1;
			}else{
			    l = m+1;
			}
		}else if(A[l] > A[m]){  // right half is sorted
		    if(A[m] < key && key <= A[r]){
			    l = m+1;
			}else{
			    r = m-1;
			}
		}else{    // e.g. {1,1,5,1,1,1} with key=5, no sense it is in which side. so the worst case is O(n)
		    l++;
		}
	}
	return -1;
}

int main(int, char**){
    string str;
    printf("please input sorted ascending array which is rotated in some where\n");
    if(getline(cin, str)==0 || str.empty())
      return 0;
    int *arr = new int[str.size()]();
    int n = splitStr2IntArray(str, arr);
    while(1){    
        printf("please input target to search:\n");
        if(getline(cin, str)==0 || str.empty())        break;
        int target = atoi(str.c_str());
        printf("index of target is %d\n", search(arr, n, target));
        
    }
    delete[] arr;
    return 0;
}
