#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool canEatAllBananas(vector<int>& piles, int k, int h) {
    int hours = 0;
    for (int pile : piles) {
        hours += (pile + k - 1) / k;  // ceil(pile / k)
        if (hours > h) return false;
    }
    return hours <= h;
}

int minEatingSpeed(vector<int>& piles, int h) {
    int left = 1, right = *max_element(piles.begin(), piles.end());

    while (left < right) {
        int mid = left + (left - right) / 2;
        if (canEatAllBananas(piles, mid, h)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    return left;
}

int main() {
    vector<int> piles = {3, 6, 7, 11};
    int h = 8;
    
    cout << "Minimum eating speed: " << minEatingSpeed(piles, h) << endl;
    
    return 0;
}
