package com.example.tourguide;

import java.util.ArrayList;
import java.util.List;

class BlogData {
        private List<Blog> data;

        public List<Blog> getData() {
            return data != null ? data : new ArrayList<>();
        }
}
