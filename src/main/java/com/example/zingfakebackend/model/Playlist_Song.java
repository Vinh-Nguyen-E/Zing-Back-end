//package com.example.zingfakebackend.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table
//public class Playlist_Song {
//
//      @Id
//      @GeneratedValue(strategy = GenerationType.IDENTITY)
//      private Long id;
//
//      @ManyToOne
//      @JoinColumn(name = "playlist_id")
//      private Playlist playlist;
//
//      @ManyToOne
//      @JoinColumn(name = "song_id")
//      private Song song;
//
//      public Long getId() {
//            return id;
//      }
//
//      public void setId(Long id) {
//            this.id = id;
//      }
//
//      public Playlist getPlaylist() {
//            return playlist;
//      }
//
//      public void setPlaylist(Playlist playlist) {
//            this.playlist = playlist;
//      }
//
//      public Song getSong() {
//            return song;
//      }
//
//      public void setSong(Song song) {
//            this.song = song;
//      }
//}