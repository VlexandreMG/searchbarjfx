package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.db.ConnectionOr;
import com.example.model.Word;

public class WordDAO {
    
    // Récupérer tous les mots
    public List<Word> getAllWords() throws SQLException {
        List<Word> words = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionOr.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT id, name FROM words ORDER BY name";
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Word word = new Word();
                word.setName(rs.getString("name"));
                words.add(word);
            }
        } finally {
            // Fermer les ressources dans l'ordre inverse de création
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
        return words;
    }
    
    // Ajouter un mot
    public void addWord(Word word) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConnectionOr.getConnection();
            String query = "INSERT INTO words (id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(2, word.getName());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    // Modifier un mot
    public void updateWord(Word word) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConnectionOr.getConnection();
            String query = "UPDATE words SET name = ? WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, word.getName());
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Aucun mot trouvé avec l'ID: ");
            }
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    // Supprimer un mot
    public void deleteWord(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConnectionOr.getConnection();
            String query = "DELETE FROM words WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, id);
            
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Aucun mot trouvé avec l'ID: " + id);
            }
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    // Rechercher des mots
    public List<Word> searchWords(String keyword) throws SQLException {
        List<Word> words = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionOr.getConnection();
            String query = "SELECT id, name FROM words WHERE UPPER(name) LIKE UPPER(?) ORDER BY name";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Word word = new Word();
                word.setName(rs.getString("name"));
                words.add(word);
            }
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
        return words;
    }
    
    // Récupérer un mot par ID
    public Word getWordById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionOr.getConnection();
            String query = "SELECT id, name FROM words WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Word word = new Word();
                word.setName(rs.getString("name"));
                return word;
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    // Compter le nombre de mots
    public int countWords() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionOr.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT COUNT(*) FROM words";
            rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    // Vérifier si un mot existe déjà
    public boolean wordExists(String wordName) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionOr.getConnection();
            String query = "SELECT COUNT(*) FROM words WHERE UPPER(name) = UPPER(?)";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, wordName);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}